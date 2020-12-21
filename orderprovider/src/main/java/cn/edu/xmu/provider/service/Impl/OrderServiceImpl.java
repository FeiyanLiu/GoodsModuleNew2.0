package cn.edu.xmu.provider.service.Impl;

import cn.edu.xmu.orderservice.client.OrderService;
import cn.edu.xmu.orderservice.model.bo.FreightModel;
import cn.edu.xmu.orderservice.model.vo.OrderItemRetVo;
import cn.edu.xmu.provider.dao.OrderDao;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "2.3.0")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public OrderItemRetVo getOrderItemById(Long id) {
        return orderDao.getOrderItemById(id);
    }

    @Override
    public List<OrderItemRetVo> getOrderItemListByUserId(Long skuId, Long userId) {
        return orderDao.getOrderItemListByUserId(skuId,userId);
    };

    @Override
    public List<OrderItemRetVo> getOrderItemListByShopId(Long skuId, Long userId) {
        return null;
    }

    @Override
    public Long getUserIdByOrderItemId(Long id) {
        return orderDao.getUserIdByOrderItemId(id);
    }

    @Override
    public Long getGoodsSkuIdByOrderItemId(Long id) {
        return orderDao.getGoodsSkuIdByOrderItemId(id);
    }

    @Override
    public FreightModel getFreightModelById(Long id) {
        return orderDao.getFreightModelById(id);
    }
}
