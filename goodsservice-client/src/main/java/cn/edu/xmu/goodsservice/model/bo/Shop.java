package cn.edu.xmu.goodsservice.model.bo;

import cn.edu.xmu.goodsservice.model.po.ShopPo;
import cn.edu.xmu.goodsservice.model.VoObject;
//import cn.edu.xmu.provider.model.vo.ShopSimpleVo;
import cn.edu.xmu.goodsservice.model.vo.ShopVo;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ruzhen Chang
 */
@Data
public class Shop implements VoObject, Serializable {


    long id;
    String shopName;
    Byte state=(byte) State.UNAUDITED.code;
    LocalDateTime gmtCreate;
    LocalDateTime gmtModified;

    public Shop() {

    }

    public enum State {
        UNAUDITED(0, "未审核"),
        OFFLINE(1, "未上线"),
        ONLINE(2, "上线"),
        DELETE(3, "关闭"),
        FAILED(4,"审核未通过");



        private static final Map<Integer, Shop.State> stateMap;

        static { //由类加载机制，静态块初始加载对应的枚举属性到map中，而不用每次取属性时，遍历一次所有枚举值
            stateMap = new HashMap<>();
            for (Shop.State enum1 : values()) {
                stateMap.put(enum1.code, enum1);
            }
        }

        private int code;
        private String description;

        State(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public static Shop.State getTypeByCode(Integer code) {
            return stateMap.get(code);
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public Shop(ShopPo po){
        this.id=po.getId();
        this.shopName = po.getName();
        this.state = po.getState();
        this.gmtCreate = po.getGmtCreate();
        this.gmtModified = po.getGmtModified();
    }


    @Override
    public ShopVo createVo() {
        return new ShopVo(this);
    }

    @Override
    public VoObject createSimpleVo() {
        //return new ShopSimpleVo(this);
        return null;
    }

    public ShopPo createPo(){
        ShopPo po=new ShopPo();
        po.setId(this.id);
        po.setName(this.shopName);
        po.setState((byte)0);
        return po;
    }


}
