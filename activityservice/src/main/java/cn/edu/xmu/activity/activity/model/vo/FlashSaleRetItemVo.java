package cn.edu.xmu.activity.model.vo;


import cn.edu.xmu.goodsservice.model.vo.GoodsSkuSimpleRetVo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-04 21:02
 */
@Data
public class FlashSaleRetItemVo {
    private Long id;

    private GoodsSkuSimpleRetVo goodsSku;

    private Long price;

    private Integer quantity;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

}
