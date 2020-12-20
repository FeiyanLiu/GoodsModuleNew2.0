package cn.edu.xmu.activity.model.bo;


import cn.edu.xmu.activity.model.po.FlashSaleItemPo;
import cn.edu.xmu.activity.model.vo.FlashSaleRetItemVo;
import cn.edu.xmu.goodsservice.model.bo.GoodsSku;
import cn.edu.xmu.goodsservice.model.vo.GoodsSkuSimpleRetVo;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-04 12:08
 */
@Data
public class FlashSaleRetItem implements VoObject {

    private Long id;

    private GoodsSkuSimpleRetVo goodsSku;

    private Long price;

    private Integer quantity;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public FlashSaleRetItem(FlashSaleItemPo po,GoodsSkuSimpleRetVo goodsSku) {
        this.id = po.getId();
        this.goodsSku = goodsSku;
        this.price = po.getPrice();
        this.quantity = po.getQuantity();
        this.gmtCreated = po.getGmtCreate();
        this.gmtModified = po.getGmtModified();
    }

    @Override
    public Object createVo() {
        FlashSaleRetItemVo flashSaleRetItemVo = new FlashSaleRetItemVo();
        flashSaleRetItemVo.setGmtCreated(this.gmtCreated);
        flashSaleRetItemVo.setGmtModified(this.gmtModified);
        flashSaleRetItemVo.setGoodsSku(this.goodsSku);
        flashSaleRetItemVo.setId(this.id);
        flashSaleRetItemVo.setPrice(this.price);
        flashSaleRetItemVo.setQuantity(this.quantity);
        return flashSaleRetItemVo;
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
