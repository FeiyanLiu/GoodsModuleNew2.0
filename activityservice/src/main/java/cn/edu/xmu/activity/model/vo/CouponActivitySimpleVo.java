package cn.edu.xmu.activity.model.vo;

import cn.edu.xmu.activity.model.bo.CouponActivity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Feiyan Liu
 * @date Created at 2020/11/30 3:30
 */
@Data
@ApiModel("优惠活动传值对象")
public class CouponActivitySimpleVo {
    @NotBlank(message = "必须输入活动名")
    @ApiModelProperty(value = "活动姓名")
    String name;

    @NotBlank(message = "必须输入优惠券数量")
    @ApiModelProperty(value = "优惠券数量，0-不用优惠券")
    int quantity;

    @Max(value = 1)
    @Min(value=0)
    @NotBlank(message = "必须输入优惠券数量类型")
    @ApiModelProperty(value = "优惠券数量类型")
    Byte quantityType;



    @NotBlank(message = "必须输入活动开始时间")
    @ApiModelProperty(value = "开始时间")
    LocalDateTime beginTime;


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
        couponActivity.setEndTime(this.endTime);
        couponActivity.setStrategy(this.strategy);
        couponActivity.setQuantity(this.quantity);
        couponActivity.setQuantityType(this.quantityType);
        return couponActivity;
    }
}
