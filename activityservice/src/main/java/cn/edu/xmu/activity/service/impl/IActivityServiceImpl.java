<<<<<<< HEAD
package cn.edu.xmu.activity.service.impl;


import cn.edu.xmu.goodsservice.client.IActivityService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author Feiyan Liu
 * @date Created at 2020/12/14 21:55
 */

@DubboService
public class IActivityServiceImpl implements IActivityService {
    @Override
    public Long getFlashSalePriceBySkuId(Long id) {
        return null;
    }
//    @Autowired
//    FlashSaleDao flashSaleDao;
//    @Override
//        public Long getFlashSalePriceBySkuId(Long id){
//            FlashSaleItemPo flashSaleItemPo = flashSaleDao.getFlashSaleItemBetweenTimeByGoodsSkuId(id, LocalDateTime.now(), LocalDateTime.now());
//            if(flashSaleItemPo != null){
//                return flashSaleItemPo.getPrice();
//            }else{
//                return null;
//            }
//    }
}
=======
package cn.edu.xmu.activity.service.impl;


import cn.edu.xmu.goodsservice.client.IActivityService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author Feiyan Liu
 * @date Created at 2020/12/14 21:55
 */

@DubboService
public class IActivityServiceImpl implements IActivityService {
    @Override
    public Long getFlashSalePriceBySkuId(Long id) {
        return null;
    }
//    @Autowired
//    FlashSaleDao flashSaleDao;
//    @Override
//        public Long getFlashSalePriceBySkuId(Long id){
//            FlashSaleItemPo flashSaleItemPo = flashSaleDao.getFlashSaleItemBetweenTimeByGoodsSkuId(id, LocalDateTime.now(), LocalDateTime.now());
//            if(flashSaleItemPo != null){
//                return flashSaleItemPo.getPrice();
//            }else{
//                return null;
//            }
//    }
}
>>>>>>> 8b6f493691a74d7b21e5f442f0e456c2b176c239
