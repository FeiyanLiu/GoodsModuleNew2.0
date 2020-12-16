package cn.edu.xmu.goodsservice.client;


import cn.edu.xmu.goodsservice.model.bo.GoodsSimpleSpu;
import cn.edu.xmu.goodsservice.model.bo.GoodsSku;
import cn.edu.xmu.goodsservice.model.bo.ShopSimple;
import cn.edu.xmu.goodsservice.model.vo.GoodsSkuSimpleRetVo;
import cn.edu.xmu.goodsservice.model.vo.ShopVo;

import java.util.List;

public interface IGoodsService {

    Long getShopIdBySpuId(Long id);

    GoodsSku getSkuById(Long id);

    ShopSimple getSimpleShopById(Long id);

    GoodsSimpleSpu getSimpleSpuById(Long id);

    List<ShopVo> getShopVoBySkuIdList(List<Long> ids);

    List<GoodsSkuSimpleRetVo> getGoodsSkuListBySkuIdList(List<Long> ids);

}
