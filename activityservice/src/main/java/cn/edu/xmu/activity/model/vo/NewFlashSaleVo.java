package cn.edu.xmu.activity.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-03 18:00
 */
@Data
public class NewFlashSaleVo {
    @NotNull
    private LocalDateTime flashDate;
}
