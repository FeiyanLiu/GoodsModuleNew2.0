package cn.edu.xmu.activity.model.vo;

import cn.edu.xmu.ooad.model.VoObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-03 14:43
 */
@Data
public class GrouponSimpleVo implements VoObject {
    private Long id;
    private String name;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    @Override
    public VoObject createVo() {
        return this;
    }

    @Override
    public VoObject createSimpleVo() {
        return this;
    }
}

