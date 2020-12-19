package cn.edu.xmu.activity.model.vo;

import cn.edu.xmu.activity.model.bo.CouponActivity;
import cn.edu.xmu.goodsservice.model.vo.ShopVo;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.otherservice.model.vo.CustomerVo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Feiyan Liu
 * @date Created at 2020/11/30 3:30
 */
@Data
public class CouponActivityRetVo implements VoObject {
    Long id;
    String name;
    byte state;
    ShopVo shop;
    int quantity;
    byte quantityType;
    byte validTerm;
    String imageUrl;
    LocalDateTime beginTime;
    LocalDateTime endTime;
    LocalDateTime couponTime;
    String strategy;
    CustomerVo createdBy;
    CustomerVo modifiedBy;
    LocalDateTime gmtCreate;
    LocalDateTime gmtModified;

    public CouponActivityRetVo(CouponActivity couponActivity) {
        this.id = couponActivity.getId();
        this.state = couponActivity.getState();
        this.name = couponActivity.getName();
        this.shop=couponActivity.getShop();
        this.quantity = couponActivity.getQuantity();
        this.quantityType = couponActivity.getQuantityType();
        this.validTerm = couponActivity.getValidTerm();
        this.imageUrl = couponActivity.getImg();
        this.beginTime = couponActivity.getBeginTime();
        this.endTime = couponActivity.getEndTime();
        this.couponTime = couponActivity.getCouponTime();
        this.strategy = couponActivity.getStrategy();
        this.createdBy = couponActivity.getCreatedBy();
        this.modifiedBy = couponActivity.getModifiedBy();
        this.gmtCreate=couponActivity.getGmtCreated();
        this.gmtModified = couponActivity.getGmtModified();
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
