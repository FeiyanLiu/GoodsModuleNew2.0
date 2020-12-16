package cn.edu.xmu.activity.service;


import cn.edu.xmu.goodsservice.client.IActivityService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author Feiyan Liu
 * @date Created at 2020/12/14 21:55
 */

@DubboService(version = "2.7.8",group = "activity-service")
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
