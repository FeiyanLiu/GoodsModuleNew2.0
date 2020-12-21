package cn.edu.xmu.goods.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;

/**
 * @author Yancheng Lai
 * createdBy Yancheng Lai 2020/12/9 9:31
 * modifiedBy Yancheng Lai 9:31
 **/
@Data
@ApiModel("品牌传值对象")
public class UpdateBrandVoBody {
    @ApiModelProperty(value = "name")
    @NotBlank
    String name;
    @ApiModelProperty(value = "detail")
    String detail;


}
