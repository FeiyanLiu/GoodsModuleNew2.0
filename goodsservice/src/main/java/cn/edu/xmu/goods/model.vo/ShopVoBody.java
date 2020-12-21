package cn.edu.xmu.goods.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Data;

@Data
@ApiModel("店铺传值对象")
public class ShopVoBody {
    @ApiModelProperty(value = "name")
    String name;

}
