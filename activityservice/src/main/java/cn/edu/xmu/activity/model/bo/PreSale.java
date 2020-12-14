package cn.edu.xmu.activity.model.bo;

import cn.edu.xmu.activity.model.po.PreSalePo;
import cn.edu.xmu.activity.model.vo.PreSaleVo;
import cn.edu.xmu.goods.model.bo.GoodsSku;
import cn.edu.xmu.goods.model.bo.Shop;
import cn.edu.xmu.goods.model.po.GoodsSkuPo;
import cn.edu.xmu.goods.model.po.ShopPo;
import cn.edu.xmu.goods.model.vo.GoodsSkuSimpleRetVo;
import cn.edu.xmu.goods.model.vo.ShopSimpleVo;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class PreSale implements VoObject {
    /**
     * 活动状态
     */

    public enum State {
        OFF(0,"已下线"),
        ON(1,"已上线"),
        DELETE(2,"已删除");

        private static final Map<Integer, State> stateMap;

        static { //由类加载机制，静态块初始加载对应的枚举属性到map中，而不用每次取属性时，遍历一次所有枚举值
            stateMap = new HashMap();
            for (PreSale.State enum1 : values()) {
                stateMap.put(enum1.code, enum1);
            }
        }

        private int code;
        private String description;

        State(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public static PreSale.State getTypeByCode(Integer code) {
            return stateMap.get(code);
        }

        public Byte getCode() {
            return (byte)code;
        }

        public String getDescription() {
            return description;
        }
    }

    public PreSale(PreSalePo po, GoodsSkuPo goodsSkuPo, ShopPo shopPo) {
        this.id = po.getId();
        this.name = po.getName();
        this.beginTime = po.getBeginTime();
        this.payTime = po.getPayTime();
        this.state = po.getState();
        this.endTime = po.getEndTime();
        this.quantity = po.getQuantity();
        this.advancePayPrice = po.getAdvancePayPrice();
        this.restPayPrice = po.getRestPayPrice();
        this.gmtCreate = po.getGmtCreate();
        this.gmtModified = po.getGmtModified();
        if(goodsSkuPo != null){
            this.goodsSku = new GoodsSku(goodsSkuPo).createSimpleVo();
        }
        if(shopPo != null){
            this.shop = new Shop(shopPo).createSimpleVo();
        }
/*        this.goodsSpuId = po.getGoodsSpuId();
        this.shopId = po.getShopId();*/
    }

    private Long id;


    private String name;

    private LocalDateTime beginTime;

    private LocalDateTime payTime;

    private LocalDateTime endTime;


    private Byte state;

/*    private Long shopId;

    private Long goodsSpuId;*/

    private GoodsSkuSimpleRetVo goodsSku;

    private ShopSimpleVo shop;

    private Integer quantity;

    private Long advancePayPrice;

    private Long restPayPrice;


    private LocalDateTime gmtCreate;


    private LocalDateTime gmtModified;



    @Override
    public Object createVo() {
        return new PreSaleVo(this);
    }

    @Override
    public Object createSimpleVo() {
        return null;
    }
}
