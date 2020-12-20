package cn.edu.xmu.goods.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("评论传值对象")
public class CommentVoBody {
    @ApiModelProperty(value = "type")
    Byte type = 0;
    @ApiModelProperty(value = "content")
    String content;
}
