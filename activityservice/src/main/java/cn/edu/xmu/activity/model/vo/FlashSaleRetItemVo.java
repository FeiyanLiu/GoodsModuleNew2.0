package cn.edu.xmu.activity.model.vo;


import cn.edu.xmu.activity.model.bo.FlashSaleRetItem;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-04 21:02
 */
@Data
public class FlashSaleRetItemVo {
    private Long id;

    private Long goodsSkuId;

    private Long price;

    private Integer quantity;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    public FlashSaleRetItemVo(FlashSaleRetItem flashSaleRetItem) {
        id = flashSaleRetItem.getId();
        goodsSkuId = flashSaleRetItem.getGoodsSkuId();
        price = flashSaleRetItem.getPrice();
        quantity = flashSaleRetItem.getQuantity();
        gmtCreated = flashSaleRetItem.getGmtCreated();
        gmtModified = flashSaleRetItem.getGmtModified();
    }

}
