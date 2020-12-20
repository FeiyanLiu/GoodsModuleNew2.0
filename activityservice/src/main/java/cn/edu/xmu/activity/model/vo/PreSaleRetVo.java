package cn.edu.xmu.activity.model.vo;



import cn.edu.xmu.activity.model.po.PreSalePo;
import cn.edu.xmu.goodsservice.model.bo.GoodsSku;
import cn.edu.xmu.goodsservice.model.bo.ShopSimple;
import cn.edu.xmu.goodsservice.model.vo.ShopVo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-02 13:16
 */
@Data
public class PreSaleRetVo {

    private Long id;

    private String name;

    private LocalDateTime beginTime;

    private LocalDateTime payTime;

    private LocalDateTime endTime;

    private GoodsSkuVo goodsSkuVo;

    private ShopVo shopVo;

    private Byte state;

    private Integer quantity;

    private Long advancePayPrice;

    private Long restPayPrice;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    public PreSaleRetVo(){};

    public PreSaleRetVo(PreSalePo preSalePo, GoodsSku goodsSku, ShopSimple shopSimple) {

        if(goodsSkuVo != null){
            this.goodsSkuVo = goodsSkuVo;
        }
        if(shopVo != null){
            this.shopVo = shopVo;
        }
        this.id = preSalePo.getId();
        this.name = preSalePo.getName();
        this.beginTime = preSalePo.getBeginTime();
        this.payTime = preSalePo.getPayTime();
        this.endTime = preSalePo.getEndTime();


        this.state = preSalePo.getState();
        this.quantity = preSalePo.getQuantity();
        this.advancePayPrice = preSalePo.getAdvancePayPrice();
        this.restPayPrice = preSalePo.getRestPayPrice();
        this.gmtCreate = preSalePo.getGmtCreate();
        this.gmtModified = preSalePo.getGmtModified();
    }
}