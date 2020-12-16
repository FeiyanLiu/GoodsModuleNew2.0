package cn.edu.xmu.goodsservice.client;


import cn.edu.xmu.goodsservice.model.bo.GoodsSimpleSpu;
import cn.edu.xmu.goodsservice.model.bo.GoodsSku;
import cn.edu.xmu.goodsservice.model.bo.ShopSimple;

public interface IGoodsService {

    Long getShopIdBySpuId(Long id);

    GoodsSku getSkuById(Long id);

    ShopSimple getSimpleShopById(Long id);

    GoodsSimpleSpu getSimpleSpuById(Long id);
}
