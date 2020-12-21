package cn.edu.xmu.activity.model.vo;

import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author LJP_3424
 * @create 2020-12-04 7:34
 */
@Data
public class NewFlashSaleItemVo {
    @NotNull
    private Long SkuId;
    @NotNull
    private Long price;
    @NotNull
    private Integer quantity;

}
