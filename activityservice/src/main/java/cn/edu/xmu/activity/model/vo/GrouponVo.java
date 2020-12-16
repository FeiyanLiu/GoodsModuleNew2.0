package cn.edu.xmu.activity.model.vo;


import cn.edu.xmu.goodsservice.model.bo.GoodsSimpleSpu;
import cn.edu.xmu.goodsservice.model.bo.ShopSimple;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author LJP_3424
 * @create 2020-12-02 20:54
 */
@Data
public class GrouponVo implements VoObject {
    private Long id;

    private String name;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Byte state;

    private ShopSimple shopSimple;

    private GoodsSimpleSpu goodsSimpleSpu;

    private String strategy;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


    @Override
    public Object createVo() {
        return null;
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}