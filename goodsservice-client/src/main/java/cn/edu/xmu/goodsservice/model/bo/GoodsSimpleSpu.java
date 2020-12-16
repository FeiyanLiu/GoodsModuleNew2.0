package cn.edu.xmu.goodsservice.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-16 13:59
 */
@Data
public class GoodsSimpleSpu {
    private Long id;
    private String name;
    private String goodsSn;
    private String imageUrl;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;
    private Byte disabled;
}
