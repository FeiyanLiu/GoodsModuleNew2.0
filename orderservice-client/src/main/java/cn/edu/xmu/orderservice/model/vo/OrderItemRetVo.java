package cn.edu.xmu.orderservice.model.vo;



import cn.edu.xmu.orderservice.model.bo.OrderItem;
import cn.edu.xmu.orderservice.model.po.OrderPo;
import lombok.Data;

@Data
public class OrderItemRetVo {
    private Long orderId;
    private String orderSn;
    private Long orderItemId;
    private Long skuId;
    private String skuName;
    private Long customerId;
    private Long shopId;

    public OrderItemRetVo(OrderItem orderItem, OrderPo order)
    {
        this.orderId=orderItem.getOrderId();
        this.orderSn=order.getOrderSn();
        this.orderItemId=orderItem.getId();
        this.skuId=orderItem.getGoodsSkuId();
        this.skuName=orderItem.getName();
        this.customerId=order.getCustomerId();
        this.shopId=order.getShopId();
    }
}
