package cn.edu.xmu.activity.model.vo;



import cn.edu.xmu.activity.model.po.PreSalePo;
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

    // private GoodsSpuVo goodsSpuVo;
    // private ShopVo shopVo;
    private Byte state;

    private Integer quantity;

    private Long advancePayPrice;

    private Long restPayPrice;

    private LocalDateTime gmtcreate;

    private LocalDateTime gmtModified;


    public PreSaleRetVo(PreSalePo preSalePo) {

        // 缺少goodsSpuVo
        // 缺少shopSpuVo
        this.id = preSalePo.getId();
        this.name = preSalePo.getName();
        this.beginTime = preSalePo.getBeginTime();
        this.payTime = preSalePo.getPayTime();
        this.state = preSalePo.getState();
        this.endTime = preSalePo.getEndTime();
        this.quantity = preSalePo.getQuantity();
        this.advancePayPrice = preSalePo.getAdvancePayPrice();
        this.restPayPrice = preSalePo.getRestPayPrice();
        this.gmtcreate = preSalePo.getGmtCreate();
        this.gmtModified = preSalePo.getGmtModified();
    }
}
