package cn.edu.xmu.activity.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-03 18:00
 */
@Data
public class NewFlashSaleVo {
    @Future
    private LocalDateTime flashDate;
}
