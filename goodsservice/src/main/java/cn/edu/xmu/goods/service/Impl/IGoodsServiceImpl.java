package cn.edu.xmu.goods.service.Impl;


import cn.edu.xmu.goods.dao.GoodsSkuDao;
import cn.edu.xmu.goods.dao.GoodsSpuDao;
import cn.edu.xmu.goods.model.bo.GoodsSku;
import cn.edu.xmu.goods.model.po.GoodsSkuPo;
import cn.edu.xmu.goods.model.po.GoodsSpuPo;
import cn.edu.xmu.goods.model.vo.GoodsSkuRetVo;
import cn.edu.xmu.goods.model.vo.GoodsSpuRetVo;
import cn.edu.xmu.goods.service.GoodsSkuService;
import cn.edu.xmu.goods.service.GoodsSpuService;
import cn.edu.xmu.ooad.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class IGoodsServiceImpl {

    @Autowired
    GoodsSkuDao goodsSkuDao;

    @Autowired
    GoodsSpuDao goodsSpuDao;
    @Transactional
    public GoodsSkuPo getSkuById(Long id){
        ReturnObject<GoodsSkuPo> goodsSkuReturnObject = goodsSkuDao.getSkuById(id);
        GoodsSkuPo goodsSkuPo = goodsSkuReturnObject.getData();
        GoodsSku goodsSku = new GoodsSku(goodsSkuPo);

        if(goodsSku != null){
            return null;
        }
        GoodsSkuRetVo retVo = goodsSku.createVo();
        GoodsSpuPo goodsSpuPo = goodsSpuDao.getGoodsSpuPoBySkuId(id).getData();
        return goodsSkuPo;
    }
}
