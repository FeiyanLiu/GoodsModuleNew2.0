package cn.edu.xmu.activity.model.bo;


import cn.edu.xmu.activity.model.po.GrouponPo;
import cn.edu.xmu.activity.model.vo.GrouponSimpleVo;
import cn.edu.xmu.activity.model.vo.GrouponVo;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LJP_3424
 * @create 2020-12-02 20:52
 */
@Data
public class Groupon implements VoObject {

    public enum State {
        OFF(0, "已下线"),
        ON(1, "已上线"),
        DELETE(2, "已删除");


        private static final Map<Integer, State> stateMap;

        static { //由类加载机制，静态块初始加载对应的枚举属性到map中，而不用每次取属性时，遍历一次所有枚举值
            stateMap = new HashMap();
            for (Groupon.State enum1 : values()) {
                stateMap.put(enum1.code, enum1);
            }
        }

        private int code;
        private String description;

        State(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public static Groupon.State getTypeByCode(Integer code) {
            return stateMap.get(code);
        }

        public byte getCode() {
            return (byte) code;
        }

        public String getDescription() {
            return description;
        }
    }

    private Long id;

    private String name;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Byte state;

 /*   private Long shopId;

    private Long goodsSpuId;*/

    private String strategy;

    private LocalDateTime gmtCreated;

    private LocalDateTime gmtModified;



    public Groupon(GrouponPo po) {
        this.gmtModified = po.getGmtModified();
        this.gmtCreated = po.getGmtCreate();
        this.endTime = po.getEndTime();
        this.beginTime = po.getBeginTime();
        this.name = po.getName();
        this.id = po.getId();
        this.state = po.getState();
        this.strategy = po.getStrategy();
    }

    @Override
    public VoObject createVo() {
        GrouponVo grouponVo = new GrouponVo();
        grouponVo.setBeginTime(this.beginTime);
        grouponVo.setEndTime(this.endTime);
        grouponVo.setGmtCreate(this.gmtCreated);
        grouponVo.setGmtModified(this.gmtModified);
        grouponVo.setId(this.id);
        grouponVo.setName(this.name);
        grouponVo.setState(this.state);
        grouponVo.setStrategy(this.strategy);
        return grouponVo;
    }

    @Override
    public VoObject createSimpleVo() {
        GrouponSimpleVo grouponSimpleVo = new GrouponSimpleVo();
        grouponSimpleVo.setId(this.id);
        grouponSimpleVo.setBeginTime(this.beginTime);
        grouponSimpleVo.setEndTime(this.endTime);
        grouponSimpleVo.setName(this.name);
        return grouponSimpleVo;
    }
}
