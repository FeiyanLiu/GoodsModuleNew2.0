package cn.edu.xmu.goods.model.vo;


import cn.edu.xmu.goods.model.bo.Shop;
import cn.edu.xmu.ooad.model.VoObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class ShopVo implements VoObject {
    @NotBlank
    @ApiModelProperty(value = "店铺名字")
    String name;
    Long id;

    public Shop createShop(){
        Shop shop=new Shop();
        shop.setShopName(this.name);
        return shop;
    }
    public ShopVo(Shop shop)
    {
        this.name=shop.getShopName();
        this.id=shop.getId();
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
