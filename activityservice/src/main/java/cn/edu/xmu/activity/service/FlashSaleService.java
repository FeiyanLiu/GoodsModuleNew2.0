package cn.edu.xmu.activity.service;

import cn.edu.xmu.activity.dao.FlashSaleDao;
import cn.edu.xmu.activity.dao.FlashSaleItemDao;
import cn.edu.xmu.activity.dao.TimeSegmentDao;
import cn.edu.xmu.activity.model.bo.FlashSale;
import cn.edu.xmu.activity.model.bo.FlashSaleItem;
import cn.edu.xmu.activity.model.bo.FlashSaleRetItem;
import cn.edu.xmu.activity.model.po.FlashSaleItemPo;
import cn.edu.xmu.activity.model.po.FlashSaleItemPoExample;
import cn.edu.xmu.activity.model.po.FlashSalePo;
import cn.edu.xmu.activity.model.po.TimeSegmentPo;
import cn.edu.xmu.activity.model.vo.FlashSaleRetItemVo;
import cn.edu.xmu.activity.model.vo.NewFlashSaleItemVo;
import cn.edu.xmu.activity.model.vo.NewFlashSaleVo;
import cn.edu.xmu.goodsservice.model.bo.GoodsSku;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LJP_3424
 * @create 2020-12-03 16:45
 */
@Service
public class FlashSaleService {
    private Logger logger = LoggerFactory.getLogger(FlashSaleService.class);

    @Autowired
    FlashSaleDao flashSaleDao;

    @Autowired
    TimeSegmentDao timeSegmentDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    FlashSaleItemDao flashSaleItemDao;
    private int flashSaleMaxSize = 2;

    @Autowired
    private ReactiveRedisTemplate<String, Serializable> reactiveRedisTemplate;

    public Flux<FlashSaleItem> getFlashSale(Long segId) {
        String segIdStr = "seg_" + segId;
        if (redisTemplate.opsForSet().size(segIdStr) == 0) {
            List<FlashSalePo> flashSalePo = flashSaleDao.getFlashSalesByTimeSegmentId(segId);
            if (flashSalePo.size() != 0) {
                List<FlashSaleItemPo> flashSaleItemPos = flashSaleItemDao.getFlashSaleItemPoFromSaleId(flashSalePo.get(0).getId());
                for (FlashSaleItemPo flashSaleItemPo : flashSaleItemPos) {
                    GoodsSku goodsSku = getGoodsSku(flashSaleItemPo.getGoodsSkuId());
                    FlashSaleItem flashSaleItem = new FlashSaleItem(flashSaleItemPo, goodsSku);
                    redisTemplate.opsForSet().add(segIdStr, flashSaleItem);
                }
            }
        }
        return reactiveRedisTemplate.opsForSet().members(segIdStr).map(x -> (FlashSaleItem) x);
    }

    @Transactional
    public ReturnObject<List> getFlashSaleById(Long id) {

        ReturnObject<List> flashSalePos = flashSaleDao.getFlashSaleById(id);
        if (flashSalePos != null) {
            //returnObject = new ReturnObject<FlashSalePo>(flashSalePo);
            return flashSalePos;
        } else {
            logger.debug("findFlashSaleById: Not Found");
            return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
    }


    // 等待goods的接口
    private List<Long> getGoodsSpuIdsToSkuIds(Long goodsSpuId) {
        List<Long> longs = new ArrayList<Long>();
        longs.add(1003L);
        longs.add(1004L);
        longs.add(1005L);
        return longs;
    }

    @Transactional
    public boolean getFlashSaleInActivities(Long goodsSpuId, LocalDateTime beginTime, LocalDateTime endTime) {
        List<Long> goodsSkuIds = getGoodsSpuIdsToSkuIds(goodsSpuId);
        for (Long goodsSkuId : goodsSkuIds) {
            if (flashSaleDao.getFlashSaleItemBetweenTimeByGoodsSkuId(goodsSkuId, beginTime, endTime) != null) {
                return true;
            }
        }
        return false;
    }


    @Transactional
    public Flux<FlashSaleItem> getCurrentFlashSale(LocalDateTime localDateTime) {
        String currentNow = "flashSaleNow_" + localDateTime.toString();
        if (redisTemplate.opsForSet().size(currentNow) == 0) {
            List<TimeSegmentPo> timeSegmentPos = timeSegmentDao.getTimeSegmentPoByTime(localDateTime);
            for (TimeSegmentPo timeSegmentPo : timeSegmentPos) {
                List<FlashSalePo> flashSalePos = flashSaleDao.getFlashSalesByTimeSegmentId(timeSegmentPo.getId());
                for (FlashSalePo flashSalePo : flashSalePos) {
                    List<FlashSaleItemPo> flashSaleItemPoFromSaleId = flashSaleItemDao.getFlashSaleItemPoFromSaleId(flashSalePo.getId());
                    for (FlashSaleItemPo flashSaleItemPo : flashSaleItemPoFromSaleId) {
                        GoodsSku goodsSku = getGoodsSku(flashSaleItemPo.getGoodsSkuId());
                        FlashSaleItem flashSaleItem = new FlashSaleItem(flashSaleItemPo, goodsSku);
                        redisTemplate.opsForSet().add(currentNow, flashSaleItem);
                    }
                }
            }
        }
        return reactiveRedisTemplate.opsForSet().members(currentNow).map(x -> (FlashSaleItem) x);
    }

    private GoodsSku getGoodsSku(Long id){
        GoodsSku goodsSku = new GoodsSku();
        goodsSku.setSkuSn("skusntest11");
        goodsSku.setId(111L);
        goodsSku.setDetail("这个是临时测试,记得调用接口");
        return goodsSku;
    }
    @Transactional
    public ReturnObject createNewFlashSale(NewFlashSaleVo vo, Long id) {
        try {
            if (flashSaleDao.countFlashSaleByTimeSegmentId(id) < flashSaleMaxSize) {
                Long flashSaleId = flashSaleDao.createNewFlashSaleByVo(vo, id);
                // 返回插入的秒杀
                return new ReturnObject<>(new FlashSale(flashSaleDao.getFlashSaleByFlashSaleId(flashSaleId)));
            } else {
                return new ReturnObject(ResponseCode.FLASHSALE_OUTLIMIT);
            }
        } catch (DataAccessException e) {
            // 数据库错误
            logger.error("数据库错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR,
                    String.format("发生了严重的数据库错误：%s", e.getMessage()));
        } catch (Exception e) {
            // 属未知错误
            logger.error("严重错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR,
                    String.format("发生了严重的未知错误：%s", e.getMessage()));
        }
    }

    /**
     * 修改活动
     *
     * @author LJP_3424
     */
    @Transactional
    public ReturnObject updateFlashSale(NewFlashSaleVo newFlashSaleVo, Long id) {
        FlashSalePo flashSalePo = flashSaleDao.getFlashSaleByFlashSaleId(id);
        try {
            if (flashSalePo == null) {
                return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            } else {
                LocalDateTime beginTime = timeSegmentDao.getBeginTimeByTimeSegmentId(flashSalePo.getTimeSegId());
                if (beginTime == null) {
                    return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
                } else {
                    LocalDateTime flashSaleBeginTime = LocalDateTime.of(flashSalePo.getFlashDate().getYear(), flashSalePo.getFlashDate().getMonth(),
                            flashSalePo.getFlashDate().getDayOfMonth(), beginTime.getHour(), beginTime.getMinute(), beginTime.getSecond());
                    if (flashSaleBeginTime.compareTo(LocalDateTime.now()) <= 0) {
                        return new ReturnObject<>(ResponseCode.TIMESEG_CONFLICT);
                    } else {
                        if (flashSaleDao.updateFlashSale(newFlashSaleVo, id) == false) {
                            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR);
                        } else {
                            return new ReturnObject<>(new FlashSale(flashSaleDao.getFlashSaleByFlashSaleId(id)));
                        }
                    }
                }
            }
        } catch (DataAccessException e) {
            // 数据库错误
            logger.error("数据库错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR,
                    String.format("发生了严重的数据库错误：%s", e.getMessage()));
        } catch (Exception e) {
            // 属未知错误
            logger.error("严重错误：" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR,
                    String.format("发生了严重的未知错误：%s", e.getMessage()));
        }
    }

    /**
     * 添加SKU
     *
     * @author LJP_3424
     */
    @Transactional
    public ReturnObject insertSkuIntoPreSale(NewFlashSaleItemVo newFlashSaleItemVo, Long id) {
        ReturnObject<List> retObj = flashSaleDao.insertSkuIntoFlashSale(newFlashSaleItemVo, id);
        return retObj;
    }

    /**
     * 分页查询所有预售活动
     *
     * @param pageNum  页数
     * @param pageSize 每页大小
     * @return ReturnObject<PageInfo < VoObject>> 分页返回预售信息
     * @author LJP_3424
     */
    public ReturnObject<PageInfo<VoObject>> selectAllFlashSale(Long id, Integer pageNum, Integer pageSize) {
        List<FlashSaleItemPo> flashSaleItemPos = null;

        logger.debug("page = " + pageNum + "pageSize = " + pageSize);
        try {
            //分页查询
            PageHelper.startPage(pageNum, pageSize);
            flashSaleItemPos = flashSaleDao.selectAllFlashSale(id, pageNum, pageSize);
            List<VoObject> ret = new ArrayList<>(flashSaleItemPos.size());
            for (FlashSaleItemPo po : flashSaleItemPos) {
                ret.add(new FlashSaleItem(po));
            }
            PageInfo<VoObject> flashSalePage = PageInfo.of(ret);
            return new ReturnObject<PageInfo<VoObject>>(flashSalePage);
        } catch (DataAccessException e) {
            logger.error("selectAllFlashSale: DataAccessException:" + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR, String.format("数据库错误：%s", e.getMessage()));
        } catch (Exception e) {
            // 其他Exception错误
            logger.error("other exception : " + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR, String.format("发生了严重的数据库错误：%s", e.getMessage()));
        }
    }

    @Transactional
    public ReturnObject deleteSkuFromFlashSale(Long fid, Long skuId) {
        ReturnObject returnObject = flashSaleItemDao.deleteFlashSaleItem(fid, skuId);
        return returnObject;
    }

    @Transactional
    public ReturnObject deleteFlashSale(Long id) {
        flashSaleItemDao.deleteFlashSaleItem(id, null);
        ReturnObject returnObject = flashSaleDao.deleteFlashSale(id);
        return returnObject;
    }
}
