package cn.edu.xmu.activity.model.bo;


import cn.edu.xmu.activity.model.po.FlashSaleItemPo;
import cn.edu.xmu.activity.model.vo.FlashSaleItemVo;
import cn.edu.xmu.activity.model.vo.FlashSaleRetItemVo;
import cn.edu.xmu.goodsservice.model.bo.GoodsSku;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-03 17:34
 */
@Data
public class FlashSaleItem implements VoObject , Serializable {

    public FlashSaleItem(FlashSaleItemPo flashSaleItemPo, GoodsSku goodsSku) {
        this.goodsSku = goodsSku;
        this.id = flashSaleItemPo.getId();
        this.saleId = flashSaleItemPo.getSaleId();
        this.gmtCreated = flashSaleItemPo.getGmtCreate();
        this.goodsSkuId = flashSaleItemPo.getGoodsSkuId();
        this.gmtModified = flashSaleItemPo.getGmtModified();
        this.price = flashSaleItemPo.getPrice();
        this.quantity = flashSaleItemPo.getQuantity();
    }
    private GoodsSku goodsSku;
    private Long id;

    private Long saleId;

    private Long goodsSkuId;

    private Long price;

    private Integer quantity;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    @Override
    public Object createVo() {
        FlashSaleRetItemVo flashSaleItemVo = new FlashSaleRetItemVo();
        flashSaleItemVo.setId(this.id);
        flashSaleItemVo.setGmtCreated(this.gmtCreated);
        flashSaleItemVo.setGmtModified(this.gmtModified);
        flashSaleItemVo.setPrice(this.price);
        flashSaleItemVo.setQuantity(this.quantity);
        flashSaleItemVo.setGoodsSkuId(this.goodsSkuId);
        return flashSaleItemVo;
    }

    public FlashSaleItem(FlashSaleItemPo flashSaleItemPo) {
        this.id = flashSaleItemPo.getId();
        this.saleId = flashSaleItemPo.getSaleId();
        this.gmtCreated = flashSaleItemPo.getGmtCreate();
        this.goodsSkuId = flashSaleItemPo.getGoodsSkuId();
        this.gmtModified = flashSaleItemPo.getGmtModified();
        this.price = flashSaleItemPo.getPrice();
        this.quantity = flashSaleItemPo.getQuantity();
    }
    public FlashSaleItem(){};

    @Override
    public VoObject createSimpleVo() {
        return null;
    }
}
