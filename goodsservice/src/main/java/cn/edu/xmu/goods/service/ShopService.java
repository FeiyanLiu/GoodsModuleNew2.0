package cn.edu.xmu.goods.service;

import cn.edu.xmu.goods.dao.GoodsSkuDao;
import cn.edu.xmu.goods.model.bo.GoodsSku;
import cn.edu.xmu.goods.model.vo.StateVo;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import cn.edu.xmu.goods.dao.ShopDao;
import cn.edu.xmu.goods.model.bo.Shop;
import cn.edu.xmu.goods.model.po.ShopPo;
import cn.edu.xmu.otherservice.model.po.ShoppingCartPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruzhen Chang
 */
@Service
public class ShopService{

    private Logger logger =  LoggerFactory.getLogger(ShopService.class);

    @Autowired
    ShopDao shopDao;
    @Autowired
    GoodsSkuDao goodsSkuDao;

    /** 
    * @Description: 得到店铺状态 
    * @Param: [] 
    * @return: cn.edu.xmu.ooad.util.ReturnObject 
    * @Author: Yancheng Lai
    * @Date: 2020/12/20 22:50
    */
    public ReturnObject getShopStates(){
        List<StateVo> lst = new ArrayList<StateVo>();

        for (Shop.State e : Shop.State.values()) {
            StateVo stateVo = new StateVo((byte)e.getCode(),e.getDescription());
            lst.add(stateVo);
        }

        return new ReturnObject<>( lst);
    }

    /**
     * @description 新建店铺
     * @author Ruzhen Chang
     */
    @Transactional
    
    public ReturnObject<VoObject> createShop(Shop shop) {
        ReturnObject returnObject = new ReturnObject();
        try {
            if (shopDao.checkShopName(shop.getShopName())) {
                return new ReturnObject<>(ResponseCode.valueOf("该姓名已被占用"));
            }
            if (shop.getId() == -1) {
                return new ReturnObject<>(ResponseCode.USER_HASSHOP);
            }
            ShopPo newShopPo=shopDao.insertShop(shop);
            shop.setId(newShopPo.getId());
            VoObject returnVo=(VoObject) shop.createRetVo();
            return new ReturnObject<VoObject>(returnVo);

        } catch (Exception e) {
            logger.error("发生了严重的服务器内部错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR);
        }
    }


    /**
     * @Description 修改店铺信息
     * @author Ruzhen Chang
     */
    @Transactional
    
    public ReturnObject updateShop(Shop shop) {
        try {
            ShopPo po = shopDao.getShopById(shop.getId());
            if (po == null) {
                return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST, String.format("店铺不存在 id：" + shop.getId()));
            }
            return shopDao.updateShop(shop);
        } catch (Exception e) {
            logger.error("发生了严重的服务器内部错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR);
        }
    }


    /**
     * @description 关闭店铺
     * @author Ruzhen Chang
     * @param shopId
     * @return
     */
    @Transactional
    
    public ReturnObject closeShop(Long shopId) {
        try {
            if (shopDao.getShopById(shopId) == null) {
                return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST, String.format("店铺 id：" + shopId));
            }

            return shopDao.closeShop(shopId);
        } catch (Exception e) {
            logger.error("发生了严重的服务器内部错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR);
        }
    }



    @Transactional
    
    public ReturnObject auditShop(Long shopId, Boolean conclusion) {
        try {
            if (shopDao.getShopById(shopId) == null) {
                return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST, String.format("店铺 id：" + shopId));
            }
            return shopDao.auditShop(shopId,conclusion);
        } catch (Exception e) {
            logger.error("发生了严重的服务器内部错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR);
        }
    }


    /**
     * @description 上线店铺
     * @author Ruzhen Chang
     * @param shopId
     * @return
     */
    @Transactional
    
    public ReturnObject onShelvesShop(Long shopId) {
        try {
            if (shopDao.getShopById(shopId) == null) {
                return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST, String.format("店铺 id：" + shopId));
            }
            return shopDao.onShelvesShop(shopId);
        } catch (Exception e) {
            logger.error("发生了严重的服务器内部错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR);
        }
    }


    /**
     * @description 下线店铺
     * @author Ruzhen Chang
     * @param shopId
     * @return
     */
    @Transactional
    
    public ReturnObject offShelvesShop(Long shopId) {
        try {
            if (shopDao.getShopById(shopId) == null) {
                return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST, String.format("店铺 id：" + shopId));
            }
            return shopDao.offShelvesShop(shopId);
        } catch (Exception e) {
            logger.error("发生了严重的服务器内部错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR);
        }
    }


    public ShopPo getShopPoById(Long id) {
        return shopDao.getShopById(id);
    }
}
