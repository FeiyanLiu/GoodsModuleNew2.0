package cn.edu.xmu.activity.model.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-19 10:30
 */
@Data
public class TimeSegment {
    private Long id;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;

}
