package cn.edu.xmu.goods.model.vo;

import cn.edu.xmu.goods.model.bo.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Ruzhen Chang
 */

@Data
@ApiModel("评论结论对象")
public class CommentConclusionVo {
    @ApiModelProperty(value = "评论类型")
   Boolean conclusion;


}
