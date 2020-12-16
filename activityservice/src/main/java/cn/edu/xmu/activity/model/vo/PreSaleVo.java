package cn.edu.xmu.activity.model.vo;



import cn.edu.xmu.activity.model.bo.PreSale;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-02 0:49
 */
@Data
public class PreSaleVo {
    private Long id;

    private String name;

    private LocalDateTime beginTime;

    private LocalDateTime payTime;

    private LocalDateTime endTime;


    private Byte state;

    private Integer quantity;

    private Long advancePayPrice;

    private Long restPayPrice;


    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    public PreSaleVo(PreSale preSale) {
        this.id = preSale.getId();
        this.name = preSale.getName();
        this.beginTime = preSale.getBeginTime();
        this.payTime = preSale.getPayTime();
        this.state = preSale.getState();
        this.endTime = preSale.getEndTime();
        this.quantity = preSale.getQuantity();
        this.advancePayPrice = preSale.getAdvancePayPrice();
        this.restPayPrice = preSale.getRestPayPrice();
        this.gmtCreate = preSale.getGmtCreate();
        this.gmtModified = preSale.getGmtModified();
    }
}