package cn.edu.xmu.goods.controller;


import cn.edu.xmu.goods.model.bo.Comment;
import cn.edu.xmu.goods.model.bo.GoodsSku;
import cn.edu.xmu.goods.model.vo.CommentVo;
import cn.edu.xmu.goods.model.vo.StateVo;
import cn.edu.xmu.goods.service.CommentService;
import cn.edu.xmu.goodsservice.model.vo.CustomerVo;
import cn.edu.xmu.ooad.annotation.Audit;
import cn.edu.xmu.ooad.annotation.Depart;
import cn.edu.xmu.ooad.annotation.LoginUser;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.ooad.util.Common;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ResponseUtil;
import cn.edu.xmu.ooad.util.ReturnObject;
import cn.edu.xmu.orderservice.client.OrderService;
import cn.edu.xmu.otherservice.client.OtherService;
import com.github.pagehelper.PageInfo;
import io.lettuce.core.dynamic.CommandMethod;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ruzhen Chang
 */

@Api(value = "评论服务",tags = "comment")
@RestController
@RequestMapping(value = "/comment",produces = "application/json;charset=UTF-8")

public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentService commentService;
    @Autowired
    private HttpServletResponse httpServletResponse;
    @DubboReference(check = false)
    OtherService otherService;
    @DubboReference(check = false)
    OrderService orderService;



    @ApiOperation(value = "买家新增sku的评论")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",dataType = "String",name="authorization",value = "Token",required = true),
            @ApiImplicitParam(paramType = "path",dataType = "integer",name="id",value = "订单明细id",required = true),
            @ApiImplicitParam(paramType = "body",dataType = "CommentVo",name="vo",value = "评价信息",required = true)
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功")
    })
    @Audit
    @GetMapping("/orderitems/{id}/comments")
    public Object addComment(@PathVariable("id")Long orderItemId,
                             BindingResult bindingResult,
                             @Validated @RequestBody CommentVo vo,
                             @LoginUser Long userId){
        Object errors = Common.processFieldErrors(bindingResult, httpServletResponse);
        if (null != errors) {
            return errors;
        }
        CustomerVo customerVo=new CustomerVo();
        customerVo.setId(userId);
        customerVo.setUserName(otherService.getUserById(userId).getUserName());
        customerVo.setName(otherService.getUserById(userId).getRealName());

        if(!userId.equals(orderService.getUserIdByOrderItemId(orderItemId)))
            return ResponseCode.USER_NOTBUY;

        Comment comment=vo.createComment();
        comment.setCustomer(customerVo);
        ReturnObject returnObject = commentService.newGoodsSkuComment(comment);

        if (returnObject.getData() != null) {
            return ResponseUtil.ok(returnObject.getData());
        } else {
            return Common.getNullRetObj(new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg()), httpServletResponse);
        }

    }


    /**
     * @description:查看sku的评论列表（已通过审核）（无需登录）
     * @author: Ruzhen Chang
     */
    @ApiOperation(value = "查看sku的评论列表（已通过审核）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "sku id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name="page", required = false, dataType="Integer", paramType="query"),
            @ApiImplicitParam(name="pageSize", required = false, dataType="Integer", paramType="query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @GetMapping("/sku/{id}/comments")
    public Object getGoodsSkuCommentList(@PathVariable(required = false) Long goodsSkuId,
                                         @RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer pageSize)
    {
        //判断商品是否存在
        page = (page == null) ? 1 : page;
        pageSize = (pageSize == null) ? 10 : pageSize;
        ReturnObject<PageInfo<VoObject>> returnObject = commentService.getGoodsSkuCommentsList(goodsSkuId,page,pageSize);
        return Common.getPageRetObject(returnObject);
    }



    @ApiOperation(value = "管理员审核评论")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "did", value = "店铺id", required = true),
            @ApiImplicitParam(paramType = "path", dataType = "Integer", name = "id", value = "评论id", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "Boolean", name = "conclusion", value = "可修改的评论信息", required = true)    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @PutMapping("/shops/{did}/comments/{id}/confirm")
    public Object auditComment(@PathVariable Long did,
                               @PathVariable Long id,
                               @Depart Long departId,
                               @Validated @RequestBody Boolean conclusion,
                               BindingResult bindingResult) {
        Object errors = Common.processFieldErrors(bindingResult, httpServletResponse);
        if (null != errors) {
            return errors;
        }
        if (departId != 0)
            return ResponseCode.AUTH_NOT_ALLOW;
        Comment comment=new Comment();
        comment.setId(id);
        comment.setGmtModified(LocalDateTime.now());
        if(conclusion){
            comment.setState((byte)Comment.State.NORM.getCode().intValue());
        }
        else {
            comment.setState((byte)Comment.State.FORBID.getCode().intValue());
        }
        ReturnObject returnObject = commentService.auditComment(comment);
        return Common.decorateReturnObject(returnObject);
    }


   /**
     * @description:买家查看自己的评价记录
     * @author: Ruzhen Chang
     */
    @ApiOperation(value = "买家查看自己的评价记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "用户token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name="page", required = false, dataType="Integer", paramType="query"),
            @ApiImplicitParam(name="pageSize", required = false, dataType="Integer", paramType="query")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
    })
    @Audit
    @GetMapping("/comments")
    public Object userGetGoodsSkuCommentList(@RequestParam(required = false) Long userId,
                                         @RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer pageSize
    ) {
        page = (page == null) ? 1 : page;
        pageSize = (pageSize == null) ? 10 : pageSize;
        ReturnObject<PageInfo<VoObject>> returnObject = commentService.getSelfCommentList(userId,page,pageSize);
        if (returnObject.getData() != null) {
            return ResponseUtil.ok(returnObject.getData());
        } else {
            return Common.getNullRetObj(new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg()), httpServletResponse);
        }
    }



    /**
     * @description:管理员查看未审核/已审核评论列表
     * @author: Ruzhen Chang
     */
    @ApiOperation(value = "管理员查看未审核/已审核评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "Token", required = true, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name="id",value = "店铺id",required = true,dataType = "Integer",paramType = "path"),
            @ApiImplicitParam(name="page", required = false, dataType="Integer", paramType="query"),
            @ApiImplicitParam(name="pageSize", required = false, dataType="Integer", paramType="query")

    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504,message="操作id不存在")
    })
    @Audit
    @GetMapping("/shops/{id}/comments/all")
    public Object getCheckedAndUncheckComment(@LoginUser Long shopId,
                                              @RequestParam(required = false) Integer page,
                                              @RequestParam(required = false) Integer pageSize
    ) {
        page = (page == null) ? 1 : page;
        pageSize = (pageSize == null) ? 10 : pageSize;
        ReturnObject<PageInfo<VoObject>> returnObject = commentService.getCommentListByShopId(shopId,page,pageSize);
        if (returnObject.getData() != null) {
            return ResponseUtil.ok(returnObject.getData());
        } else {
            return Common.getNullRetObj(new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg()), httpServletResponse);
        }
    }


    /**
     * @description 获取评论的所有状态
     * @author Ruzhen Chang
     * @return
     */
    @ApiOperation(value = "获得评论的所有状态")
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
    })
    @GetMapping("/comments/states")
    public  Object getCommentStates() {
        ReturnObject<List<StateVo>> returnObject = commentService.findCommentStates();
        return Common.decorateReturnObject(returnObject);
    }


}
