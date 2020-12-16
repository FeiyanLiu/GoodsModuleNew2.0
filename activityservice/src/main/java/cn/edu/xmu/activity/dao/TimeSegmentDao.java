package cn.edu.xmu.activity.dao;

import cn.edu.xmu.activity.mapper.TimeSegmentPoMapper;
import cn.edu.xmu.activity.model.po.TimeSegmentPo;
import cn.edu.xmu.activity.model.po.TimeSegmentPoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LJP_3424
 * @create 2020-12-08 1:00
 */
@Repository
public class TimeSegmentDao {
    @Autowired
    private TimeSegmentPoMapper timeSegmentPoMapper;

    public List<TimeSegmentPo> getTimeSegmentPoByTime(LocalDateTime localDateTime){
        TimeSegmentPoExample timeSegmentPoExample = new TimeSegmentPoExample();
        TimeSegmentPoExample.Criteria criteria = timeSegmentPoExample.createCriteria();
        criteria.andEndTimeGreaterThanOrEqualTo(localDateTime);
        criteria.andBeginTimeLessThanOrEqualTo(localDateTime);
        List<TimeSegmentPo> timeSegmentPos = timeSegmentPoMapper.selectByExample(timeSegmentPoExample);
        return timeSegmentPos;
    }
    public LocalDateTime getBeginTimeByTimeSegmentId(Long id){
        LocalDateTime beginTime = timeSegmentPoMapper.selectByPrimaryKey(id).getBeginTime();
        return beginTime;
    }
}
