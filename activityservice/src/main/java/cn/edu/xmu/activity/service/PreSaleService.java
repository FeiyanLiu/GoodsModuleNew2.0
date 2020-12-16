<<<<<<< HEAD
package cn.edu.xmu.activity.service;

import cn.edu.xmu.activity.dao.PreSaleDao;
import cn.edu.xmu.activity.model.bo.PreSale;
import cn.edu.xmu.activity.model.po.PreSalePo;
import cn.edu.xmu.activity.model.vo.NewPreSaleVo;
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
    public ReturnObject<List> getPreSaleById(Long id, Byte state) {
        ReturnObject<List> returnObject = preSaleDao.getPreSaleBySpuId(id, state);
        if (returnObject.getCode() != ResponseCode.OK) {
            // 存在错误则直接返回
            return returnObject;
        }
        List<PreSalePo> preSalePos = returnObject.getData();
        List<PreSale> preSales = new ArrayList<>(preSalePos.size());
        for (PreSalePo preSalePo : preSalePos) {
            PreSale preSale = createPreSaleByPreSalePo(preSalePo);
            preSales.add(preSale);
        }
        return new ReturnObject<>(preSales);
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
        List<PreSalePo> preSalePos = preSaleDao.selectAllPreSale(shopId, timeline, spuId, pageNum, pageSize);
        List<VoObject> ret = new ArrayList<>(preSalePos.size());
        for (PreSalePo preSalePo : preSalePos) {
            PreSale preSale = createPreSaleByPreSalePo(preSalePo);
            ret.add(preSale);
        }
        PageHelper.startPage(pageNum, pageSize);

        PageInfo<VoObject> of = PageInfo.of(ret);
        return new ReturnObject<PageInfo<VoObject>>(of);
    }

    /**
     * @Description: 新增preSale
     * @param vo
     * @param shopId
     * @param id
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
        // 检查商品在beginTime 和 endTime是否参与了活动
        if (getPreSaleInActivities(id, vo.getBeginTime(), vo.getEndTime()) == true) {
            return new ReturnObject(ResponseCode.TIMESEG_CONFLICT);
        }
        PreSalePo newPreSalePo = preSaleDao.createNewPreSaleByVo(vo, shopId, id);
        // 如果插入成功,返回ID
        if(newPreSalePo != null){
            PreSale newPreSale = createPreSaleByPreSalePo(newPreSalePo);
            return new ReturnObject<>(newPreSale);
        }else{
            return new ReturnObject(ResponseCode.INTERNAL_SERVER_ERR);
        }
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
        PreSalePo po = preSaleDao.getPreSalePo(id);
        // 检查预售活动存在性(不存在或者已删除状态都会返回false)
        if (po == null || po.getState() == PreSale.State.DELETE.getCode()) {
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
        // 新版本下,预售活动信息下线状态可以改动,不需要看时间,只要在上线状态即使时间外也不行.
        if (po.getState() == PreSale.State.ON.getCode()) {
            return new ReturnObject<>(ResponseCode.PRESALE_STATENOTALLOW);
        }
        preSaleDao.updatePreSale(newPreSaleVo, id);
        PreSalePo preSalePo = preSaleDao.getPreSalePo(id);
        PreSale preSale = createPreSaleByPreSalePo(preSalePo);
        retObj = new ReturnObject<>(preSale);
        return retObj;

    }

    /**
     * 构造retVo
     * @return
     */
    private PreSale createPreSaleByPreSalePo(PreSalePo preSalePo) {
        GoodsSku sku = goodsService.getSkuById(preSalePo.getGoodsSkuId());
        ShopSimple simpleShop = goodsService.getSimpleShopById(preSalePo.getShopId());

        PreSale preSale = new PreSale(preSalePo, sku, simpleShop);
        return preSale;
    }

    @Transactional
    public ReturnObject<Object> changePreSaleState(Long id, byte state) {
        return preSaleDao.changePreSaleState(id, state);
    }

}
=======
package cn.edu.xmu.activity.service;

import cn.edu.xmu.activity.dao.PreSaleDao;
import cn.edu.xmu.activity.model.bo.PreSale;
import cn.edu.xmu.activity.model.po.PreSalePo;
import cn.edu.xmu.activity.model.vo.NewPreSaleVo;
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
    public ReturnObject<List> getPreSaleById(Long id, Byte state) {
        ReturnObject<List> returnObject = preSaleDao.getPreSaleBySpuId(id, state);
        if (returnObject.getCode() != ResponseCode.OK) {
            // 存在错误则直接返回
            return returnObject;
        }
        List<PreSalePo> preSalePos = returnObject.getData();
        List<PreSale> preSales = new ArrayList<>(preSalePos.size());
        for (PreSalePo preSalePo : preSalePos) {
            PreSale preSale = createPreSaleByPreSalePo(preSalePo);
            preSales.add(preSale);
        }
        return new ReturnObject<>(preSales);
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
        List<PreSalePo> preSalePos = preSaleDao.selectAllPreSale(shopId, timeline, spuId, pageNum, pageSize);
        List<VoObject> ret = new ArrayList<>(preSalePos.size());
        for (PreSalePo preSalePo : preSalePos) {
            PreSale preSale = createPreSaleByPreSalePo(preSalePo);
            ret.add(preSale);
        }
        PageHelper.startPage(pageNum, pageSize);

        PageInfo<VoObject> of = PageInfo.of(ret);
        return new ReturnObject<PageInfo<VoObject>>(of);
    }

    /**
     * @Description: 新增preSale
     * @param vo
     * @param shopId
     * @param id
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
        // 检查商品在beginTime 和 endTime是否参与了活动
        if (getPreSaleInActivities(id, vo.getBeginTime(), vo.getEndTime()) == true) {
            return new ReturnObject(ResponseCode.TIMESEG_CONFLICT);
        }
        PreSalePo newPreSalePo = preSaleDao.createNewPreSaleByVo(vo, shopId, id);
        // 如果插入成功,返回ID
        if(newPreSalePo != null){
            PreSale newPreSale = createPreSaleByPreSalePo(newPreSalePo);
            return new ReturnObject<>(newPreSale);
        }else{
            return new ReturnObject(ResponseCode.INTERNAL_SERVER_ERR);
        }
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
        PreSalePo po = preSaleDao.getPreSalePo(id);
        // 检查预售活动存在性(不存在或者已删除状态都会返回false)
        if (po == null || po.getState() == PreSale.State.DELETE.getCode()) {
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
        // 新版本下,预售活动信息下线状态可以改动,不需要看时间,只要在上线状态即使时间外也不行.
        if (po.getState() == PreSale.State.ON.getCode()) {
            return new ReturnObject<>(ResponseCode.PRESALE_STATENOTALLOW);
        }
        preSaleDao.updatePreSale(newPreSaleVo, id);
        PreSalePo preSalePo = preSaleDao.getPreSalePo(id);
        PreSale preSale = createPreSaleByPreSalePo(preSalePo);
        retObj = new ReturnObject<>(preSale);
        return retObj;

    }

    /**
     * 构造retVo
     * @return
     */
    private PreSale createPreSaleByPreSalePo(PreSalePo preSalePo) {
        GoodsSku sku = goodsService.getSkuById(preSalePo.getGoodsSkuId());
        ShopSimple simpleShop = goodsService.getSimpleShopById(preSalePo.getShopId());

        PreSale preSale = new PreSale(preSalePo, sku, simpleShop);
        return preSale;
    }

    @Transactional
    public ReturnObject<Object> changePreSaleState(Long id, byte state) {
        return preSaleDao.changePreSaleState(id, state);
    }

}
>>>>>>> 8b6f493691a74d7b21e5f442f0e456c2b176c239
