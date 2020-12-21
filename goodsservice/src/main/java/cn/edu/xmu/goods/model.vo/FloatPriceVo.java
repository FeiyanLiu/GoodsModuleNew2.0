package cn.edu.xmu.goods.model.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FloatPriceVo implements Serializable {
    private Long activityPrice;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    @Min(0)
    private Integer quantity;

    public FloatPriceVo(){}
}
