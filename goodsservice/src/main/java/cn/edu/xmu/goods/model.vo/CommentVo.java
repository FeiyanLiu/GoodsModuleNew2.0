package cn.edu.xmu.goods.model.vo;

import cn.edu.xmu.goods.model.bo.Comment;
import io.lettuce.core.StrAlgoArgs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.print.DocFlavor;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author Ruzhen Chang
 */

@Data
@ApiModel("评论传值对象")
public class CommentVo {
    @NotBlank(message = "必须输入评论类型")
    @ApiModelProperty(value = "评论类型")
    Byte type;

    @NotBlank(message = "必须输入评论内容")
    @ApiModelProperty(value = "评论内容")
    String content;

    public Comment createComment(){
        Comment comment=new Comment();
        comment.setType(this.type);
        comment.setContent(this.content);

        return comment;

    }


}
