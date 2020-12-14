package cn.edu.xmu.goodsservice.client;


import cn.edu.xmu.goodsservice.model.po.GoodsSkuPo;

public interface IGoodsService {

    public GoodsSkuPo getSkuById(Long id);
}
