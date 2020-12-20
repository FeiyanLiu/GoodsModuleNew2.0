package cn.edu.xmu.activity.model.vo;



import cn.edu.xmu.goodsservice.model.bo.ShopSimple;
import cn.edu.xmu.goodsservice.model.vo.GoodsSkuSimpleRetVo;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-02 0:49
 */
@Data
public class PreSaleVo implements VoObject{
    private Long id;

    private String name;

    private LocalDateTime beginTime;

    private LocalDateTime payTime;

    private LocalDateTime endTime;

    private Byte state;

    private Integer quantity;

    private Long advancePayPrice;

    private Long restPayPrice;

    private GoodsSkuSimpleRetVo goodsSku;

    private ShopSimple shop;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


    public PreSaleVo() {

    }

    @Override
    public Object createVo() {
        return null;
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}