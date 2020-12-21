package cn.edu.xmu.goods.controller;

import cn.edu.xmu.goods.model.vo.GoodsCategoryRetVo;
import cn.edu.xmu.goods.model.vo.UpdateBrandVoBody;
import cn.edu.xmu.goods.service.GoodsCategoryService;
import cn.edu.xmu.ooad.annotation.Audit;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：谢沛辰
 * @Date: 2020.11.30
 * @Description:类别控制器
 */
@Api(value = "商品服务", tags = "goods")
@RestController
@RequestMapping(value = "/goods",produces = "application/json;charset=UTF-8")
public class GoodsCategoryController {
    private  static  final Logger logger = LoggerFactory.getLogger(GoodsCategoryController.class);

    @Autowired
    GoodsCategoryService goodsCategoryService;

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: null
     * @Return:
     * @Description:创建商品子分类
     */
    @Audit
    @ApiOperation(value = "新增商品类目")
    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name="id",required = true,dataType = "int",paramType = "path"),
            @ApiImplicitParam(name="shopId",required = true,dataType = "int",paramType = "path"),
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=504,message = "操作id不存在")
    })
    @PostMapping("/shops/{shopId}/categories/{id}/subcategories")
    public Object createCategory(@Validated @RequestBody GoodsCategoryRetVo vo,
                                 BindingResult bindingResult,
                                 @PathVariable Long id,
                                 @PathVariable Long shopId){
        if(shopId != 0){
            return Common.decorateReturnObject(new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE));
        }
        if(vo.getName().contentEquals("")){
            return Common.decorateReturnObject(new ReturnObject<>(ResponseCode.FIELD_NOTVALID));
        }
        if(logger.isDebugEnabled()){
            logger.debug("create Category: id = "+id);
        }
        vo.setPId(id);

        ReturnObject<GoodsCategoryRetVo> result=goodsCategoryService.createCategory(vo);
        if(result.getCode()== ResponseCode.OK){
            ResponseEntity res = new ResponseEntity(
                    ResponseUtil.ok(result),
                    HttpStatus.CREATED);
            return res;
        } else
        {
            return Common.decorateReturnObject(result);
        }

    }

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: null
     * @Return:
     * @Description:查找商品子分类
     */
    @ApiOperation(value = "查询商品分类关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name="id",required = true,dataType = "int",paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=504,message = "操作id不存在")
    })
    @GetMapping("/categories/{id}/subcategories")
    public Object findSubCategories(@PathVariable Long id){
        if(logger.isDebugEnabled()){
            logger.debug("select category : pid ="+id);
        }
        ReturnObject<List<GoodsCategoryRetVo>> result=goodsCategoryService.findSubCategory(id);
        return Common.decorateReturnObject(result);
    }

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: null
     * @Return:
     * @Description:修改商品类别
     */
    @ApiOperation(value = "修改商品类目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name="id",required = true,dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "shopId",required = true,dataType = "Long",paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=504,message = "操作id不存在")
    })
    @Audit
    @PutMapping("/shops/{shopId}/categories/{id}")
    public Object updateCategory(@Validated @RequestBody GoodsCategoryRetVo vo,
                                 BindingResult bindingResult,
                                 @PathVariable Long id,
                                 @PathVariable Long shopId){
        if(shopId != 0){
            return (new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE));
        }
        if(logger.isDebugEnabled()){
            logger.debug("update category : id ="+id);
        }
        vo.setId(id);
        ReturnObject result=goodsCategoryService.updateCategory(vo);
        return Common.decorateReturnObject(result);
    }

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: null
     * @Return:
     * @Description:删除商品类别
     */
    @ApiOperation(value = "删除商品类目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",required = true,dataType = "String",paramType = "header"),
            @ApiImplicitParam(name="id",required = true,dataType = "int",paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code=0,message = "成功"),
            @ApiResponse(code=504,message = "操作id不存在")
    })
    @Audit
    @DeleteMapping("/shops/{shopId}/categories/{id}")
    public Object deleteCategory(@PathVariable Long id,
                                 @PathVariable Long shopId){
        if(shopId != 0){
            return (new ReturnObject<>(ResponseCode.RESOURCE_ID_OUTSCOPE));
        }
        if(logger.isDebugEnabled()){
            logger.debug("delete category : id ="+id);
        }
        ReturnObject<Object> result=goodsCategoryService.deleteCategory(id);
        return Common.decorateReturnObject(result);
    }
}
