package cn.edu.xmu.goods.model.vo;

import cn.edu.xmu.goods.model.bo.FloatPrice;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;
//@Data
//class createdBy{
//    private Long id;
//    private String userName;
//    //用户名暂时不添加
//    public void setId(Long id){
//        this.id=id;
//    }
//}
//
//@Data
//class Person{
//    private Long id;
//    private String userName;
//    //用户名暂时不添加
//    public void setId(Long id){
//        this.id=id;
//    }
//}

@Data
public class FloatPriceRetVo implements Serializable {
    private Long id;
    private Long activityPrice;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private LocalDateTime gmtCreated;
    private LocalDateTime gmtModified;
    private Byte valid;
    public UserVo createdBy=new UserVo();
    public UserVo modifiedBy=new UserVo();
    @Min(0)
    private Integer quantity;
    public FloatPriceRetVo(){}
    public FloatPriceRetVo(FloatPrice floatPrice,Long createdId,Long modifiedId,String username){
        setId(floatPrice.getId());
        setActivityPrice(floatPrice.getActivityPrice());
        setBeginTime(floatPrice.getBeginTime());
        setEndTime(floatPrice.getEndTime());
        setGmtCreated(floatPrice.getGmtCreated());
        setGmtModified(floatPrice.getGmtModified());
        setQuantity(floatPrice.getQuantity());
        setValid(floatPrice.getValid());
        createdBy.setId(createdId);
        modifiedBy.setId(modifiedId);
        createdBy.setUserName(username);
        modifiedBy.setUserName(username);
    }
}




