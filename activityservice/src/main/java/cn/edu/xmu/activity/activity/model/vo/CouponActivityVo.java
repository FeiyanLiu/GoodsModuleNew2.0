package cn.edu.xmu.activity.model.vo;

import cn.edu.xmu.activity.model.bo.CouponActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Feiyan Liu
 * @date Created at 2020/11/30 3:30
 */
@Data
@ApiModel("优惠活动传值对象")
public class CouponActivityVo {
    @NotBlank(message = "必须输入活动名")
    @ApiModelProperty(value = "活动姓名")
    String name;

    @NotBlank(message = "必须输入优惠券数量")
    @ApiModelProperty(value = "优惠券数量，0-不用优惠券")
    int quantity;

    @NotBlank(message = "必须输入优惠券数量类型")
    @ApiModelProperty(value = "优惠券数量类型")
    Byte quantityType;

    @NotBlank(message = "必须输入优惠券有效时间")
    @ApiModelProperty(value = "优惠券从领取那天起的有效时间，0-与活动同，其他：从领券起的有效天数")
    Byte validTerm;

    @NotBlank(message = "必须输入活动开始时间")
    @ApiModelProperty(value = "开始时间")
    LocalDateTime beginTime;

    @NotBlank(message = "必须输入开始领优惠券时间")
    @ApiModelProperty(value = "开始领优惠券时间")
    LocalDateTime couponTime;

    @NotBlank(message = "必须输入结束时间")
    @ApiModelProperty(value = "结束时间")
    LocalDateTime endTime;

    @NotBlank(message = "必须输入优惠规则")
    @ApiModelProperty(value = "优惠规则JSON")
    String strategy;

    public CouponActivity createCouponActivity() {
        CouponActivity couponActivity = new CouponActivity();
        couponActivity.setName(this.name);
        couponActivity.setBeginTime(this.beginTime);
        couponActivity.setCouponTime(this.couponTime);
        couponActivity.setEndTime(this.endTime);
        couponActivity.setStrategy(this.strategy);
        couponActivity.setQuantity(this.quantity);
        couponActivity.setQuantityType(this.quantityType);
        couponActivity.setValidTerm(this.validTerm);
        return couponActivity;
    }
}
