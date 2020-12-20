package cn.edu.xmu.activity.model.vo;

import cn.edu.xmu.activity.model.bo.CouponActivity;
import cn.edu.xmu.goodsservice.model.vo.ShopVo;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.otherservice.model.vo.CustomerVo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Feiyan Liu
 * @date Created at 2020/11/30 3:30
 */
@Data
public class AdminVo implements VoObject {
    Long id;
    String name;


    @Override
    public Object createVo() {
        return null;
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
