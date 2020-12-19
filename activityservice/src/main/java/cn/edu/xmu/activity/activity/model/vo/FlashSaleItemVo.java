package cn.edu.xmu.activity.model.vo;


import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-04 12:10
 */
@Data
public class FlashSaleItemVo implements VoObject {

    private Long id;

    private GoodsSkuVo goodsSkuVo;

    private Long price;

    private Integer quantity;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

    @Override
    public Object createVo() {
        return null;
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
