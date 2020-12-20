package cn.edu.xmu.goods.model.bo;


import cn.edu.xmu.goods.model.po.CommentPo;
import cn.edu.xmu.goods.model.vo.CommentRetVo;
import cn.edu.xmu.goodsservice.model.vo.CustomerVo;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ruzhen Chang
 */
@Data
public class Comment implements VoObject{

    private Long id;
    private CustomerVo customer;
    private Long goodsSkuId;
    private Byte type;
    /*评价等级 0好评1中评2差评*/
    private String content;
    private Byte state=(byte) State.NEW.code;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    public Comment() {}

    public VoObject createRetVo() {
        return new CommentRetVo(this);
    }

    /**
     * @author Ruzhen Chang
     */

    public enum State {
        NEW(0, "未审核"),
        NORM(1, "评论成功"),
        FORBID(2, "未通过");

        private static final Map<Integer, Comment.State> stateMap;

        static { //由类加载机制，静态块初始加载对应的枚举属性到map中，而不用每次取属性时，遍历一次所有枚举值
            stateMap = new HashMap();
            for (Comment.State enum1 : values()) {
                stateMap.put(enum1.code, enum1);
            }
        }

        private int code;
        private String description;

        State(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public static Comment.State getTypeByCode(Integer code) {
            return stateMap.get(code);
        }

        public Integer getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public Comment(CommentPo po){
        this.id=po.getId();
        this.customer.setId(po.getCustomerId());
        this.goodsSkuId= po.getGoodsSkuId();
        this.type= po.getType();
        this.content= po.getContent();
        this.state=po.getState();
        this.gmtCreate=po.getGmtCreate();
        this.gmtModified=po.getGmtModified();
    }

    public CommentPo createPo(){
        CommentPo commentPo=new CommentPo();
        commentPo.setId(this.id);
        commentPo.setContent(this.content);
        commentPo.setCustomerId(customer.getId());
        commentPo.setGoodsSkuId(this.goodsSkuId);
        commentPo.setType(this.type);
        commentPo.setState(this.state);
        commentPo.setGmtCreate(this.gmtCreate);
        commentPo.setGmtModified(this.gmtModified);
        return commentPo;
    }

    @Override
    public Object createVo() {
        return null;
    }

    @Override
    public VoObject createSimpleVo() {
        return null;
    }
}
