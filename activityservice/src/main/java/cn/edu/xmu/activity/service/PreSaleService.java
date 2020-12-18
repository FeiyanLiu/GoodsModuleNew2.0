package cn.edu.xmu.activity.service;

import cn.edu.xmu.activity.dao.PreSaleDao;
import cn.edu.xmu.activity.model.bo.PreSale;
import cn.edu.xmu.activity.model.po.PreSalePo;
import cn.edu.xmu.activity.model.vo.NewPreSaleVo;
import cn.edu.xmu.activity.model.vo.PreSaleRetVo;
import cn.edu.xmu.activity.model.vo.PreSaleVo;
import cn.edu.xmu.goodsservice.client.IGoodsService;
import cn.edu.xmu.goodsservice.model.bo.GoodsSku;
import cn.edu.xmu.goodsservice.model.bo.ShopSimple;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LJP_3424
 * @create 2020-12-01 14:25
 */
@Service
public class PreSaleService {
    private Logger logger = LoggerFactory.getLogger(PreSaleService.class);

    @Autowired
    PreSaleDao preSaleDao;

    @DubboReference(check = false)
    IGoodsService goodsService;


    @Transactional
    public boolean getPreSaleInActivities(Long goodsSpuId, LocalDateTime beginTime, LocalDateTime endTime) {
        return preSaleDao.getPreSaleInActivities(goodsSpuId, beginTime, endTime);
    }


    @Transactional
    public ReturnObject<List> getPreSaleById(Long shopId, Long id, Byte state) {
        ReturnObject<List> returnObject = preSaleDao.getPreSaleBySpuId(shopId, id, state);
        if (returnObject.getCode() != ResponseCode.OK) {
            // 存在错误则直接返回
            return new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg());
        }
        List<PreSalePo> preSalePos = returnObject.getData();

        List<VoObject> voObjects = new ArrayList<>(preSalePos.size());
        for (PreSalePo preSalePo : preSalePos) {
            GoodsSku goodsSku = linshiNewGoodsSku(preSalePo.getGoodsSkuId());
            ShopSimple shopSimple = linshiNewShopSimple(preSalePo.getShopId());
            VoObject voObject = new PreSale(preSalePo, goodsSku, shopSimple).createVo();
            voObjects.add(voObject);
        }
        return new ReturnObject<>(voObjects);
    }

    /**
     * 分页查询所有预售活动
     *
     * @param pageNum  页数
     * @param pageSize 每页大小
     * @return ReturnObject<PageInfo < VoObject>> 分页返回预售信息
     * @author LJP_3424
     */
    @Transactional
    public ReturnObject<PageInfo<VoObject>> selectAllPreSale(Long shopId, Byte timeline, Long spuId, Integer pageNum, Integer pageSize) {
        ReturnObject<PageInfo<PreSalePo>> returnPreSalePoPage = preSaleDao.selectAllPreSale(shopId, timeline, spuId, pageNum, pageSize);
        if (returnPreSalePoPage.getCode().equals(ResponseCode.OK) == false) {
            return new ReturnObject<>(returnPreSalePoPage.getCode(), returnPreSalePoPage.getErrmsg());
        }

        PageInfo<PreSalePo> preSalePosPageInfo = returnPreSalePoPage.getData();
        PageHelper.startPage(pageNum, pageSize);
        List<VoObject> voObjects = new ArrayList<>(preSalePosPageInfo.getSize());
        for (PreSalePo preSalePo : preSalePosPageInfo.getList()) {
            // 目前暂时关闭 dubbo,后续连接上后再取消
            // GoodsSku goodsSku = goodsService.getSkuById(preSalePo.getGoodsSkuId());
            // ShopSimple shopSimple = goodsService.getSimpleShopById(preSalePo.getShopId());
            GoodsSku goodsSku = linshiNewGoodsSku(preSalePo.getGoodsSkuId());
            ShopSimple shopSimple = linshiNewShopSimple(preSalePo.getShopId());
            VoObject voObject = new PreSale(preSalePo, goodsSku, shopSimple).createVo();
            voObjects.add(voObject);
        }

        PageInfo<VoObject> of = PageInfo.of(voObjects);
        of.setPageSize(preSalePosPageInfo.getPageSize());
        of.setTotal(preSalePosPageInfo.getTotal());
        of.setPageNum(preSalePosPageInfo.getPageNum());
        of.setSize(preSalePosPageInfo.getSize());
        return new ReturnObject<PageInfo<VoObject>>(of);
    }

    /**
     * @param vo
     * @param shopId
     * @param id
     * @Description: 新增preSale
     * @return: cn.edu.xmu.ooad.util.ReturnObject
     * @Author: LJP_3424
     * @Date: 2020/12/11 16:00
     */
    @Transactional
    public ReturnObject createNewPreSale(NewPreSaleVo vo, Long shopId, Long id) {
        // 检查商品skuId是否为真
        GoodsSku sku = goodsService.getSkuById(id);
        if (sku == null) {
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
        // 商品是否为自家商品
        GoodsSku goodsSku = goodsService.getSkuById(id);
        if(shopId != goodsService.getShopIdBySpuId(goodsSku.getGoodsSpuId())){
            return new ReturnObject(ResponseCode.RESOURCE_ID_OUTSCOPE);
        }
        // 检查商品在beginTime 和 endTime是否参与了活动
        if (getPreSaleInActivities(id, vo.getBeginTime(), vo.getEndTime()) == true) {
            return new ReturnObject(ResponseCode.TIMESEG_CONFLICT);
        }
        ReturnObject<PreSalePo> returnObject = preSaleDao.createNewPreSaleByVo(vo, shopId, id);
        if (returnObject.getCode() != ResponseCode.OK) {
            // 存在错误则直接返回
            return new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg());
        }
        PreSalePo newPreSalePo = returnObject.getData();
        // 如果插入成功,获取信息,GoodsSku 前面已经获取
        ShopSimple shopSimple = goodsService.getSimpleShopById(shopId);
        VoObject vo1 = new PreSale(newPreSalePo, goodsSku, shopSimple).createVo();
        return new ReturnObject<>(vo1);
    }

    /**
     * @param newPreSaleVo
     * @param id
     * @Description: 更新活动信息
     * @return: cn.edu.xmu.ooad.util.ReturnObject
     * @Author: LJP_3424
     * @Date: 2020/12/7 20:09
     */
    @Transactional
    public ReturnObject updatePreSale(NewPreSaleVo newPreSaleVo, Long id) {
        ReturnObject retObj = null;
        ReturnObject<PreSalePo> returnObject = preSaleDao.getPreSalePo(id);
        if (returnObject.getCode() != ResponseCode.OK) {
            // 存在错误则直接返回
            return new ReturnObject<>(returnObject.getCode(), returnObject.getErrmsg());
        }
        PreSalePo po = returnObject.getData();

        // 新版本下,预售活动信息下线状态可以改动,不需要看时间,只要在上线状态即使时间外也不行.
        if (po.getState() == PreSale.State.ON.getCode()) {
            return new ReturnObject<>(ResponseCode.PRESALE_STATENOTALLOW);
        }
        ReturnObject returnObject1 = preSaleDao.updatePreSale(newPreSaleVo, id);
        if(returnObject1.getCode() != ResponseCode.OK){
            return returnObject1;
        }

        ReturnObject<PreSalePo> returnObject2 = preSaleDao.getPreSalePo(id);
        if(returnObject2.getCode() != ResponseCode.OK){
            return new ReturnObject(returnObject2.getCode(),returnObject2.getErrmsg());
        }
        PreSalePo preSalePo = returnObject2.getData();
        GoodsSku goodsSku = linshiNewGoodsSku(preSalePo.getGoodsSkuId());
        ShopSimple shopSimple = linshiNewShopSimple(preSalePo.getShopId());
        PreSale newPreSale = new PreSale(preSalePo, goodsSku, shopSimple);
        retObj = new ReturnObject<>(newPreSale);
        return retObj;
    }


    @Transactional
    public ReturnObject changePreSaleState(Long id, Byte state) {
        ReturnObject<PreSalePo> returnObject = preSaleDao.getPreSalePo(id);
        if(returnObject.getCode() != ResponseCode.OK){
            return returnObject;
        }
        PreSalePo data = returnObject.getData();
        if(data!=null)
        return preSaleDao.changePreSaleState(id, state);
        else
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
    }


    /*******************临时岗*********************/

    private GoodsSku linshiNewGoodsSku(Long id) {
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setDetail("临时创建的,后面记得改成调用商品接口");
        goodsSku.setName("临时测试");
        goodsSku.setId(id);
        return goodsSku;
    }

    private ShopSimple linshiNewShopSimple(Long id) {
        ShopSimple shopSimple = new ShopSimple();
        shopSimple.setShopName("临时店铺");
        shopSimple.setId(id);
        return shopSimple;
    }
    /****************************************/


}