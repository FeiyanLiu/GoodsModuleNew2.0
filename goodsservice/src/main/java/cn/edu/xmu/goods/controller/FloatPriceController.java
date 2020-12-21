package cn.edu.xmu.goods.controller;

import cn.edu.xmu.goods.model.vo.FloatPriceRetVo;
import cn.edu.xmu.goods.model.vo.FloatPriceVo;
import cn.edu.xmu.goods.service.FloatPriceService;
import cn.edu.xmu.goods.service.GoodsSkuService;
import cn.edu.xmu.ooad.annotation.Audit;
import cn.edu.xmu.ooad.annotation.LoginUser;
import cn.edu.xmu.ooad.util.Common;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ResponseUtil;
import cn.edu.xmu.ooad.util.ReturnObject;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author：谢沛辰
 * @Date: 2020.12.01
 * @Description:价格浮动控制器
 */
@RestController
@RequestMapping(value="/goods",produces = "application/json;charset+UTF-8")
public class FloatPriceController {
    private  static  final Logger logger = LoggerFactory.getLogger(FloatPriceController.class);

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    FloatPriceService floatPriceService;
    
    @Autowired
    GoodsSkuService goodsSkuService;

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: null
     * @Return:
     * @Description:删除价格浮动
     */
    @ApiOperation(value = "失效价格浮动")
    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name="shopId",required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name="id",required = true,dataType = "int",paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=504,message = "操作id不存在")
    })
    @Audit
    @DeleteMapping("/shops/{shopId}/floatPrices/{id}")
    public Object deleteFloatPrice(@LoginUser Long userId, @PathVariable("id") Long id,@PathVariable("shopId") Long shopId){
        ReturnObject<Object> result = floatPriceService.logicallyDelete(userId,id,shopId);
        return Common.decorateReturnObject(result);
    }

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: null
     * @Return:
     * @Description:创建价格浮动
     */
    @ApiOperation(value = "新增价格浮动")
    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name="shopId",required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name="id",required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name="floatPriceVo",required = true,dataType = "FloatPriceVo",paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
    })
    @Audit
    @PostMapping("/shops/{shopId}/skus/{id}/floatPrices")
    public Object createFloatPrice(@LoginUser Long userId, @PathVariable("id") Long id, @PathVariable("shopId") Long shopId,@RequestBody FloatPriceVo floatPriceVo,BindingResult bindingResult){
        Object errors = Common.processFieldErrors(bindingResult, httpServletResponse);
        if(floatPriceVo.getQuantity()<0){
            return new ResponseEntity(
                    ResponseUtil.fail(ResponseCode.FIELD_NOTVALID),
                    HttpStatus.BAD_REQUEST);
        }
        if (null != errors) {
            return errors;
        }
        ReturnObject<FloatPriceRetVo> result =floatPriceService.createFloatPrice(shopId,userId,floatPriceVo,id);
        if(result.getCode()==ResponseCode.RESOURCE_ID_OUTSCOPE){
            return new ResponseEntity(
                    ResponseUtil.fail(result.getCode(), result.getErrmsg()),
                    HttpStatus.FORBIDDEN);
        }
        if(result.getCode()== ResponseCode.OK){
            ResponseEntity res = new ResponseEntity(ResponseUtil.ok(result.getData()), HttpStatus.CREATED);
            return res;
        }
        return Common.decorateReturnObject(result);
    }
}
