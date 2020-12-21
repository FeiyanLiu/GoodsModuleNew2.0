package cn.edu.xmu.goods.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author：谢沛辰
 * @Date: 2020.11.29
 * @Description:
 */
@Data
public class GoodsCategoryRetVo implements Serializable {
    private Long id;
    private Long pId;
    @NotBlank
    private String name;
    private String gmtCreate;
    private String gmtModified;

    public GoodsCategoryRetVo(){}
}
