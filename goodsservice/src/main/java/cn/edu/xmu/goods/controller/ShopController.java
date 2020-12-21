package cn.edu.xmu.goods.controller;

import cn.edu.xmu.goods.model.bo.Shop;
import cn.edu.xmu.goods.model.vo.ShopSimpleVo;
import cn.edu.xmu.goods.model.vo.ShopVo;
import cn.edu.xmu.goods.service.GoodsSpuService;
import cn.edu.xmu.goods.service.ShopService;
import cn.edu.xmu.ooad.annotation.Audit;
import cn.edu.xmu.ooad.annotation.Depart;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
* @author Ruzhen Chang
 */

@Api(value = "店铺",tags = "shop")
@RestController
@RequestMapping(value = "/shop",produces = "application/json;charset=UTF-8")

public class ShopController {
    private static final Logger logger =LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private GoodsSpuService goodsSpuService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private HttpServletResponse httpServletResponse;


    /**
     * 店家申请店铺
     * @author Ruzhen Chang
     */
    @ApiOperation(value = "店家申请店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "ShopVo", name = "vo", value = "店铺信息", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
    })
    @Audit
    @PostMapping("/shops")
    public Object insertShop(@Validated @RequestBody ShopVo shopVo,
                             BindingResult bindingResult,
                             @PathVariable Long id,
                             @Depart Long departId) {
        Object errors = Common.processFieldErrors(bindingResult, httpServletResponse);
        if (null != errors) {
            return errors;
        }
        Shop shop =shopVo.createShop();
        if(shop.getId()==-1)
            return ResponseCode.USER_HASSHOP;
        ReturnObject returnObject = shopService.createShop(shop);
        if (returnObject.getData() != null) {
            return ResponseUtil.ok(returnObject.getData());
        } else {
            return Common.getNullRetObj(new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg()), httpServletResponse);
        }
    }




    @ApiOperation(value = "店家修改店铺信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "店铺id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "shopSimpleVo", value = "店铺信息", required = true, dataType = "shopSimpleVo", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @PutMapping("/shops/{shopId}")
    public Object updateShop(@PathVariable Long shopId,
                             @Depart Long departId,
                             @Validated @RequestBody ShopSimpleVo vo,
                             BindingResult bindingResult) {
        Object errors = Common.processFieldErrors(bindingResult, httpServletResponse);
        if (null != errors) {
            return errors;
        }
        if(!departId.equals(shopId))
            return ResponseCode.AUTH_NOT_ALLOW;
        Shop shop = vo.createShop();
        shop.setId(shopId);
        ReturnObject returnObject = shopService.updateShop(shop);
        if (returnObject.getData() != null) {
            return ResponseUtil.ok(returnObject.getData());
        } else {
            return Common.getNullRetObj(new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg()), httpServletResponse);
        }
    }




    @ApiOperation(value = "管理员或店家关闭店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "店铺id", required = true, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @DeleteMapping("/shops/{id}")
    public Object userCloseShop(@PathVariable Long shopId, @Depart Long departId) {
        if (shopId.equals(departId)) {
            ReturnObject closeShopReturn = shopService.closeShop(shopId);
            ReturnObject skuDisableReturn = goodsSpuService.setSkuDisabledByShopId(shopId);
            if (closeShopReturn.getData() != null && skuDisableReturn.getData() !=null) {
                return Common.getRetObject(closeShopReturn);
            }
            if(closeShopReturn.getCode() == ResponseCode.OK){
                return new ResponseEntity(
                        ResponseUtil.fail(ResponseCode.OK, "该ID对应的SKU不存在"),
                        HttpStatus.CREATED);
            }
            else if(closeShopReturn.getData()==null){
                return Common.getNullRetObj(new ReturnObject<>(closeShopReturn.getCode(), closeShopReturn.getErrmsg()), httpServletResponse);
            }
            else {
                return Common.getNullRetObj(new ReturnObject<>(skuDisableReturn.getCode(),skuDisableReturn.getErrmsg()),httpServletResponse);
            }
        } else {
            return Common.getNullRetObj(new ReturnObject<>(ResponseCode.FIELD_NOTVALID, String.format("departId不匹配")), httpServletResponse);
        }
    }



    @ApiOperation(value = "平台管理员审核店铺信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "部门id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "id", value = "店铺id", required = true, dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(paramType = "body", dataType = "Boolean", name = "conclusion", value = "审核信息", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @DeleteMapping("/shops/{shopId}/newshops/{id}/audit")
    public Object userAuditShop(@PathVariable Long id, @PathVariable Long shopId,@RequestBody Boolean conclusion,@Depart Long deptId) {
        if (id.equals(deptId)) {
            ReturnObject returnObject = shopService.auditShop(shopId,conclusion);
            if (returnObject.getData() != null) {
                return Common.getRetObject(returnObject);
            } else {
                return Common.getNullRetObj(new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg()), httpServletResponse);
            }
        } else {
            return Common.getNullRetObj(new ReturnObject<>(ResponseCode.FIELD_NOTVALID, String.format("departId不匹配")), httpServletResponse);
        }
    }




    @ApiOperation(value = "管理员上线店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "店铺id", required = true, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @DeleteMapping("/shops/{id}/onshelves")
    public Object userOnShelvesShop(@PathVariable Long shopId, @Depart Long departId) {
        if (shopId.equals(departId)) {
            ReturnObject returnObject = shopService.onShelvesShop(shopId);
            if (returnObject.getData() != null) {
                return Common.getRetObject(returnObject);
            } else {
                return Common.getNullRetObj(new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg()), httpServletResponse);
            }
        } else {
            return Common.getNullRetObj(new ReturnObject<>(ResponseCode.FIELD_NOTVALID, String.format("departId不匹配")), httpServletResponse);
        }
    }

    @ApiOperation(value = "获取店铺状态")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @GetMapping("/shops/states")
    public Object getShopState() {
                return Common.decorateReturnObject(shopService.getShopStates());
    }




    @ApiOperation(value = "管理员下线店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "authorization", value = "Token", required = true),
            @ApiImplicitParam(name = "shopId", value = "店铺id", required = true, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 504, message = "操作id不存在")
    })
    @Audit
    @DeleteMapping("/shops/{id}/offshelves")
    public Object userOffShelvesShop(@PathVariable Long shopId, @Depart Long departId) {
        if (shopId.equals(departId)) {
            ReturnObject offShelvesShopReturn = shopService.offShelvesShop(shopId);
            ReturnObject offShelvesGoodsSkuReturn = goodsSpuService.setAllSkuOffShelvesByShopId(shopId);

            if (offShelvesShopReturn.getData() != null && offShelvesGoodsSkuReturn.getData() != null) {
                return Common.getRetObject(offShelvesShopReturn);
            }
            else if(offShelvesShopReturn==null) {
                return Common.getNullRetObj(new ReturnObject<>(offShelvesShopReturn.getCode(), offShelvesShopReturn.getErrmsg()), httpServletResponse);
            }
            else {
                return  Common.getNullRetObj(new ReturnObject<>(offShelvesGoodsSkuReturn.getCode(),offShelvesGoodsSkuReturn.getErrmsg()),httpServletResponse);
            }
        } else {
            return Common.getNullRetObj(new ReturnObject<>(ResponseCode.FIELD_NOTVALID, String.format("departId不匹配")), httpServletResponse);
        }
    }

}
