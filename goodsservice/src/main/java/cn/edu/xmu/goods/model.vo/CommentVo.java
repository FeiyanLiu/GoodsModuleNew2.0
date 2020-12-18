package cn.edu.xmu.goods.model.vo;

import cn.edu.xmu.goods.model.bo.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

/**
 * @author Ruzhen Chang
 */

@Data
@ApiModel("评论传值对象")
public class CommentVo {
    @ApiModelProperty(value = "评论id")
    Long id;
    //@ApiModelProperty(value = "顾客")
    //simpleUser
    @ApiModelProperty(value = "评论的SKU id")
    Long goodsSkuId;
    @ApiModelProperty(value = "评价类型 0好评，1中评，2差评")
    Byte type;
    @ApiModelProperty(value = "内容")
    String content;
    @ApiModelProperty(value = "状态")
    Comment.State state;
    @ApiModelProperty(value = "创建时间")
    LocalDateTime gmtCreate;
    @ApiModelProperty(value = "修改时间")
    LocalDateTime gmtModified;

    public Comment createComment(){
        Comment comment=new Comment();
        comment.setId(this.id);
        comment.setGoodsSkuId(this.goodsSkuId);
        comment.setType(this.type);
        comment.setContent(this.content);
        comment.setState(this.state);
        return comment;

    }


}
