package cn.edu.xmu.goodsservice.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LJP_3424
 * @create 2020-12-16 13:06
 */
@Data
public class ShopSimple implements Serializable {
    Long id;
    String shopName;
    public ShopSimple(){};
}
