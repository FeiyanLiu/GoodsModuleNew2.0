package cn.edu.xmu.activity.service.impl;


import cn.edu.xmu.activity.dao.FlashSaleDao;
import cn.edu.xmu.activity.model.po.FlashSaleItemPo;
import cn.edu.xmu.activity.service.FlashSaleService;
import cn.edu.xmu.goodsservice.client.IActivityService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmu.otherservice.model.po.TimeSegmentPo;

import java.time.LocalDateTime;

/**
 * @author Feiyan Liu
 * @date Created at 2020/12/14 21:55
 */

@DubboService(version = "2.7.8",group = "goods-service")
public class IActivityServiceImpl implements IActivityService {
    @Autowired
    FlashSaleService flashSaleService;
    @Override
    public Long getFlashSalePriceBySkuId(Long id) {
        TimeSegmentPo timeSegmentPo = flashSaleService.returnCurrentTimeSegmentPo();
        if (timeSegmentPo != null) {
            FlashSaleItemPo flashSaleItemPo = flashSaleService.getFlashSaleItemPoByTimeSegmentAndGoodsSkuId(timeSegmentPo.getId(), id);
            if (flashSaleItemPo != null) {
                return flashSaleItemPo.getPrice();
            }
        }
        return null;
    }
}
