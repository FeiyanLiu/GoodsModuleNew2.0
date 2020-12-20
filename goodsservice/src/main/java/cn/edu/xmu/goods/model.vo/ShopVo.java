package cn.edu.xmu.goods.model.vo;


import cn.edu.xmu.goods.model.bo.Shop;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class ShopVo {
    @NotBlank
    @ApiModelProperty(value = "店铺名字")
    String shopName;
    Long id;

    public Shop createShop(){
        Shop shop=new Shop();
        shop.setShopName(this.shopName);
        return shop;
    }
    public ShopVo(Shop shop)
    {
        this.shopName=shop.getShopName();
        this.id=shop.getId();
    }

}
