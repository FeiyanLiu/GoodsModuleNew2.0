package cn.edu.xmu.activity.dao;


import cn.edu.xmu.activity.mapper.FlashSaleItemPoMapper;
import cn.edu.xmu.activity.mapper.FlashSalePoMapper;
import cn.edu.xmu.activity.mapper.TimeSegmentPoMapper;
import cn.edu.xmu.activity.model.bo.FlashSale;
import cn.edu.xmu.activity.model.po.*;
import cn.edu.xmu.activity.model.vo.FlashSaleDataVo;
import cn.edu.xmu.activity.model.vo.NewFlashSaleItemVo;
import cn.edu.xmu.activity.model.vo.NewFlashSaleVo;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LJP_3424
 * @create 2020-12-03 16:44
 */
@Repository
public class FlashSaleDao implements InitializingBean {

    @Autowired
    private FlashSalePoMapper flashSalePoMapper;

    @Autowired
    private FlashSaleItemPoMapper flashSaleItemPoMapper;

    @Autowired
    private TimeSegmentPoMapper timeSegmentPoMapper;

    private static final Logger logger = LoggerFactory.getLogger(FlashSaleDao.class);


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    /**
     * @param goodsSpuId
     * @Description: 未提供SPU-->SKUS接口,因此先暂时写一个临时函数
     * @return: java.util.List<java.lang.Long>
     * @Author: LJP_3424
     * @Date: 2020/12/6 1:01
     */
    private List<Long> goodsSpuIdsToSkuIds(Long goodsSpuId) {
        List<Long> longs = new ArrayList<Long>();
        longs.add(1003L);
        longs.add(1004L);
        longs.add(1005L);
        return longs;
    }

    //! 临时更改,需要重新写
    public FlashSaleItemPo getFlashSaleItemBetweenTimeByGoodsSkuId(Long goodsSkuId, LocalDateTime beginTime, LocalDateTime endTime) {

        // SKUId-->SaleId-->timeSegmentId-->beginTime&&endTime 耗时较长
        /* 取出所有的 timeSegmentID --> beginTime && endTime
         * SkuId-->SaleId-->-->timeSegmentId--Map-->beginTime&&endTime 减少数据库访问次数
         */
        FlashSaleItemPoExample flashSaleItemPoExample = new FlashSaleItemPoExample();
        FlashSaleItemPoExample.Criteria criteriaItem = flashSaleItemPoExample.createCriteria();

        // List 转Map
        List<TimeSegmentPo> timeSegmentPos = getAllTimeSegment();
        Map<Long, TimeSegmentPo> mappedTimeSegmentPo = timeSegmentPos.stream().collect(Collectors.toMap(TimeSegmentPo::getId, (p) -> p));

        criteriaItem.andGoodsSkuIdEqualTo(goodsSkuId);
        List<FlashSaleItemPo> flashSaleItemPos = flashSaleItemPoMapper.selectByExample(flashSaleItemPoExample);
        for (FlashSaleItemPo flashSaleItemPo : flashSaleItemPos) {
            FlashSalePo flashSalePo = flashSalePoMapper.selectByPrimaryKey(flashSaleItemPo.getSaleId());
            LocalDateTime timeSegmentBeginTime = mappedTimeSegmentPo.get(flashSalePo.getTimeSegId()).getBeginTime();
            LocalDateTime timeSegmentEndTime = mappedTimeSegmentPo.get(flashSalePo.getTimeSegId()).getEndTime();
            LocalDateTime flashSalePoBeginTime = LocalDateTime.of(flashSalePo.getFlashDate().getYear(),
                    flashSalePo.getFlashDate().getMonth(), flashSalePo.getFlashDate().getDayOfMonth(),
                    timeSegmentBeginTime.getHour(), timeSegmentBeginTime.getMinute(), timeSegmentBeginTime.getSecond());
            LocalDateTime flashSalePoEndTime = LocalDateTime.of(flashSalePo.getFlashDate().getYear(),
                    flashSalePo.getFlashDate().getMonth(), flashSalePo.getFlashDate().getDayOfMonth(),
                    timeSegmentEndTime.getHour(), timeSegmentEndTime.getMinute(), timeSegmentEndTime.getSecond());
            if (flashSalePoBeginTime.compareTo(endTime) < 0 && flashSalePoEndTime.compareTo(beginTime) > 0) {
                return flashSaleItemPo;
            }
        }
        return null;
    }

    public List<TimeSegmentPo> getAllTimeSegment() {
        TimeSegmentPoExample timeSegmentPoExample = new TimeSegmentPoExample();
        TimeSegmentPoExample.Criteria criteria = timeSegmentPoExample.createCriteria();
        // 取出所有的秒杀时段
        criteria.andTypeEqualTo((byte) 1);
        List<TimeSegmentPo> timeSegmentPos = timeSegmentPoMapper.selectByExample(timeSegmentPoExample);
        return timeSegmentPos;
    }
/*
*
 * @Description:
 *
 * @return: void
 * @Author: LJP_3424
 * @Date: 2020/12/6 1:02
    public void insertTimeSegment() {
        TimeSegmentPo timeSegmentPo = new TimeSegmentPo();
        LocalDateTime beginTime = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2020, 1, 1, 0, 29, 59);
        for (int i = 1; i <= 24; i++) {
            for (int j = 1; j <= 2; j++) {
                timeSegmentPo.setId((long) (i * 100 + j));
                timeSegmentPo.setBeginTime(beginTime);
                timeSegmentPo.setGmtCreated(LocalDateTime.now());
                timeSegmentPo.setEndTime(endTime);
                beginTime = beginTime.plusMinutes(30);
                endTime = endTime.plusMinutes(30);
                timeSegmentPoMapper.insert(timeSegmentPo);
            }
        }
    }
*/
/**
 * @Description: 获取当前时段的秒杀活动
 * @param localDateTime
 * @return: cn.edu.xmu.ooad.util.ReturnObject<java.util.List>
 * @Author: LJP_3424
 * @Date: 2020/12/6 1:03
 */
/*    public ReturnObject<List> getCurrentFlashSale(LocalDateTime localDateTime) {
        TimeSegmentPoExample timeSegmentPoExample = new TimeSegmentPoExample();
        TimeSegmentPoExample.Criteria criteria = timeSegmentPoExample.createCriteria();
        criteria.andBeginTimeLessThanOrEqualTo(localDateTime);
        criteria.andEndTimeGreaterThanOrEqualTo(localDateTime);
        List<TimeSegmentPo> timeSegmentPos = timeSegmentPoMapper.selectByExample(timeSegmentPoExample);
        return getFlashSaleById(timeSegmentPos.get(0).getId());
    }*/

    /**
     * @param id
     * @Description: 通过商品SKUID 获取商品历史秒杀信息
     * @return: cn.edu.xmu.ooad.util.ReturnObject<java.util.List>
     * @Author: LJP_3424
     * @Date: 2020/12/6 1:03
     */
    public ReturnObject<List> getFlashSaleById(Long id) {
        FlashSalePoExample example = new FlashSalePoExample();
        FlashSalePoExample.Criteria criteria = example.createCriteria();
        criteria.andTimeSegIdEqualTo(id);
        logger.debug("findFlashSaleById: TimeSegmentId = " + id);
        List<FlashSalePo> flashSalePos = flashSalePoMapper.selectByExample(example);
        List<FlashSaleDataVo> flashSaleDataVos = new ArrayList<FlashSaleDataVo>();
        for (FlashSalePo po : flashSalePos) {
            FlashSaleItemPoExample flashSaleItemPoExample = new FlashSaleItemPoExample();
            FlashSaleItemPoExample.Criteria criteria_item = flashSaleItemPoExample.createCriteria();
            criteria_item.andSaleIdEqualTo(po.getId());
            List<FlashSaleItemPo> flashSaleItemPos = flashSaleItemPoMapper.selectByExample(flashSaleItemPoExample);
            for (FlashSaleItemPo itemPo : flashSaleItemPos) {
                FlashSaleDataVo vo = new FlashSaleDataVo(itemPo);
                flashSaleDataVos.add(vo);
            }
        }
        if (flashSalePos.size() != 0) {
            return new ReturnObject<>(flashSaleDataVos);
        } else {
            return null;
        }
    }


    /**
     * @param vo
     * @param id
     * @Description: 通过Vo验证时段冲突后创建新的秒杀
     * @return: cn.edu.xmu.ooad.util.ReturnObject<java.util.List>
     * @Author: LJP_3424
     * @Date: 2020/12/6 1:04
     */
    public Long createNewFlashSaleByVo(NewFlashSaleVo vo, Long id) {
        FlashSalePo flashSalePo = new FlashSalePo();
        flashSalePo.setFlashDate(vo.getFlashDate());
        flashSalePo.setTimeSegId(id);
        flashSalePo.setGmtCreate(LocalDateTime.now());
        flashSalePo.setState(FlashSale.State.OFF.getCode());
        flashSalePoMapper.insert(flashSalePo);
        return flashSalePo.getId();
    }

    public ReturnObject<Boolean> checkFlashSaleEnough(Long id, LocalDateTime flashDate) {
        FlashSalePoExample example = new FlashSalePoExample();
        FlashSalePoExample.Criteria criteria = example.createCriteria();
        criteria.andTimeSegIdEqualTo(id);
        criteria.andFlashDateEqualTo(flashDate);
        criteria.andStateNotEqualTo(FlashSale.State.DELETE.getCode());
        logger.debug("findFlashSaleById: Time" + "SegmentId = " + id);
        List<FlashSalePo> flashSalePos = null;
        try {
            flashSalePos = flashSalePoMapper.selectByExample(example);
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
        if (flashSalePos.size() > 0) {
            return new ReturnObject<Boolean>(true);
        } else {
            return new ReturnObject<Boolean>(false);
        }
    }

    public ReturnObject<FlashSalePo> getFlashSaleByFlashSaleId(Long flashSaleId) {
        try {
            return new ReturnObject<>(flashSalePoMapper.selectByPrimaryKey(flashSaleId));
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
     * @param flashSaleVo
     * @param id
     * @Description: 修改秒杀信息
     * @return: cn.edu.xmu.ooad.util.ReturnObject<cn.edu.xmu.ooad.model.VoObject>
     * @Author: LJP_3424
     * @Date: 2020/12/6 1:04
     */
    public ReturnObject updateFlashSale(NewFlashSaleVo flashSaleVo, Long id) {
        FlashSalePo flashSalePo = new FlashSalePo();
        flashSalePo.setFlashDate(flashSaleVo.getFlashDate());
        flashSalePo.setGmtModified(LocalDateTime.now());
        flashSalePo.setId(id);
        try {
            int ret = flashSalePoMapper.updateByPrimaryKeySelective(flashSalePo);
            if (ret == 0) {
                return new ReturnObject(ResponseCode.INTERNAL_SERVER_ERR,
                        "插入失败");
            }
        } catch (DataAccessException e) {
            // 数据库错误
            logger.error("数据库错误：" + e.getMessage());
            return new ReturnObject(ResponseCode.INTERNAL_SERVER_ERR,
                    String.format("发生了严重的数据库错误：%s", e.getMessage()));
        } catch (Exception e) {
            // 属未知错误
            logger.error("严重错误：" + e.getMessage());
            return new ReturnObject(ResponseCode.INTERNAL_SERVER_ERR,
                    String.format("发生了严重的未知错误：%s", e.getMessage()));
        }
        return new ReturnObject();
    }

    public ReturnObject<FlashSaleItemPo> insertSkuIntoFlashSale(NewFlashSaleItemVo newFlashSaleItemVo, Long id) {
        FlashSaleItemPo flashSaleItemPo = new FlashSaleItemPo();
        flashSaleItemPo.setQuantity(newFlashSaleItemVo.getQuantity());
        flashSaleItemPo.setSaleId(id);
        flashSaleItemPo.setGoodsSkuId(newFlashSaleItemVo.getSkuId());
        flashSaleItemPo.setPrice(newFlashSaleItemVo.getPrice());
        flashSaleItemPo.setGmtCreate(LocalDateTime.now());
        try {
            flashSaleItemPoMapper.insert(flashSaleItemPo);
            return new ReturnObject<>(flashSaleItemPo);
        } catch (DataAccessException e) {
            // 数据库错误
            logger.error("数据库错误：" + e.getMessage());
            return new ReturnObject(ResponseCode.INTERNAL_SERVER_ERR,
                    String.format("发生了严重的数据库错误：%s", e.getMessage()));
        } catch (Exception e) {
            // 属未知错误
            logger.error("严重错误：" + e.getMessage());
            return new ReturnObject(ResponseCode.INTERNAL_SERVER_ERR,
                    String.format("发生了严重的未知错误：%s", e.getMessage()));
        }
    }


    /**
     * @param id
     * @param pageNum
     * @param pageSize
     * @Description: 分页查询所有秒杀信息
     * @return: cn.edu.xmu.ooad.util.ReturnObject<com.github.pagehelper.PageInfo < cn.edu.xmu.ooad.model.VoObject>>
     * @Author: LJP_3424
     * @Date: 2020/12/6 1:05
     */

    public List<FlashSaleItemPo> selectAllFlashSale(Long id, Integer pageNum, Integer pageSize) {
        FlashSaleItemPoExample flashSaleItemPoExample = new FlashSaleItemPoExample();
        FlashSaleItemPoExample.Criteria criteriaItem = flashSaleItemPoExample.createCriteria();
        criteriaItem.andSaleIdEqualTo(id);
        PageHelper.startPage(pageNum, pageSize);
        List<FlashSaleItemPo> flashSaleItemPos = flashSaleItemPoMapper.selectByExample(flashSaleItemPoExample);
        return flashSaleItemPos;
    }

    public List<FlashSalePo> getFlashSalesByTimeSegmentId(Long timeSegmentId) {
        FlashSalePoExample flashSalePoExample = new FlashSalePoExample();
        FlashSalePoExample.Criteria criteria = flashSalePoExample.createCriteria();
        criteria.andTimeSegIdEqualTo(timeSegmentId);
        return flashSalePoMapper.selectByExample(flashSalePoExample);
    }

    public ReturnObject deleteFlashSale(Long id) {
        int ret = flashSalePoMapper.deleteByPrimaryKey(id);
        if (ret == 0) {
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
        return new ReturnObject(ResponseCode.OK);
    }

    public ReturnObject<Long> insertFlashSale(NewFlashSaleVo vo, Long timeSegmentId) {

        FlashSalePo flashSalePo = new FlashSalePo();
        flashSalePo.setFlashDate(vo.getFlashDate());
        flashSalePo.setGmtCreate(LocalDateTime.now());
        flashSalePo.setState(FlashSale.State.ON.getCode());
        flashSalePo.setTimeSegId(timeSegmentId);

        try {
            int ret = flashSalePoMapper.insertSelective(flashSalePo);
            if (ret == 1) {
                return new ReturnObject<Long>(flashSalePo.getId());
            } else {
                return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR, "插入失败");
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

    public ReturnObject changeFlashSaleState(Long id, Byte state) {
        FlashSalePo flashSalePo = new FlashSalePo();
        flashSalePo.setId(id);
        flashSalePo.setState(state);
        try {
            int ret = flashSalePoMapper.updateByPrimaryKeySelective(flashSalePo);
            if(ret != 0){
                return new ReturnObject(ResponseCode.OK);
            }else{
                return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR,
                        String.format("插入失败"));
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
}
