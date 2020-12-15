package cn.edu.xmu.goodsservice.client;


import cn.edu.xmu.goodsservice.model.bo.GoodsSku;

public interface IGoodsService {

    GoodsSku getSkuById(Long id);
}
