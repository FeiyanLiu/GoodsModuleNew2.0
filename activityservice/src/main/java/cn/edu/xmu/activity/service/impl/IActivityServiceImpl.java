package cn.edu.xmu.activity.service.impl;


import cn.edu.xmu.activity.dao.FlashSaleDao;
import cn.edu.xmu.activity.model.po.FlashSaleItemPo;
import cn.edu.xmu.goodsservice.client.IActivityService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author Feiyan Liu
 * @date Created at 2020/12/14 21:55
 */

@DubboService(version = "2.7.8")
public class IActivityServiceImpl implements IActivityService {
    @Autowired
    FlashSaleDao flashSaleDao;
    @Override
        public Long getFlashSalePriceBySkuId(Long id){
            FlashSaleItemPo flashSaleItemPo = flashSaleDao.getFlashSaleItemBetweenTimeByGoodsSkuId(id, LocalDateTime.now(), LocalDateTime.now());
            if(flashSaleItemPo != null){
                return flashSaleItemPo.getPrice();
            }else{
                return null;
            }
    }
}
