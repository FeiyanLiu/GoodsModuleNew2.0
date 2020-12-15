package cn.edu.xmu.goods.model.bo;

import cn.edu.xmu.goods.model.po.GoodsSpuPo;
import cn.edu.xmu.goods.model.vo.*;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.goods.model.po.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yancheng Lai
 * createdBy Yancheng Lai 2020/12/1 19:48
 * modifiedBy Yancheng Lai 19:48
 **/
@Data
public class GoodsSku implements VoObject, Serializable {

    String skuSn;
    String name;
    Long originalPrice;
    String configuration;
    Long weight;
    String imageUrl;
    Integer inventory;
    String detail;
    LocalDateTime gmtCreate;
    LocalDateTime gmtModified;
    Byte disabled;


    private Byte statecode;
//    State state;
    public enum State {
        WAITING(0, "未上架"),
        INVALID(4, "上架"),
        DELETED(6, "已删除");

        private static final Map<Integer, State> stateMap;

        static { //由类加载机制，静态块初始加载对应的枚举属性到map中，而不用每次取属性时，遍历一次所有枚举值
            stateMap = new HashMap();
            for (State enum1 : values()) {
                stateMap.put(enum1.code, enum1);
            }
        }

        private int code;
        private String description;

        State(int code, String description) {
            this.code = code;
            this.description = description;
        }


        public static State getTypeByCode(Byte code) {
            return stateMap.get(code);
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    @Override
    public GoodsSkuRetVo createVo() {
        return new GoodsSkuRetVo(this);
    }

    @Override
    public GoodsSkuSimpleRetVo createSimpleVo() {
        return new GoodsSkuSimpleRetVo(this);
    }



    /**
    * @Description: 构造函数by Po
    * @Param: [goodsSkuPo]
    * @return:  GoodsSku
    * @Author: Yancheng Lai
    * @Date: 2020/12/1 19:56
    */
    public GoodsSku(GoodsSkuPo goodsSkuPo) {
        this.statecode = goodsSkuPo.getState();
    }
    

    public GoodsSku(GoodsSkuVo goodsSkuVo){
        this.setSkuSn(goodsSkuVo.getSn());
        this.setName(goodsSkuVo.getName());
        this.setOriginalPrice(goodsSkuVo.getOriginalPrice());
        this.setConfiguration(goodsSkuVo.getConfiguration());
        this.setWeight(goodsSkuVo.getWeight());
        this.setImageUrl(goodsSkuVo.getImageUrl());
        this.setInventory(goodsSkuVo.getInventory());
        this.setDetail(goodsSkuVo.getDetail());
        this.setGmtCreate(LocalDateTime.now());
        this.setGmtModified(LocalDateTime.now());
    }

    /**
    * @Description: Vo构造 
    * @Param: [goodsSkuRetVo] 
    * @return:  
    * @Author: Yancheng Lai
    * @Date: 2020/12/3 15:41
    */
    private Long id;
    private Long goodsSpuId;
    public GoodsSku(GoodsSkuRetVo goodsSkuRetVo){
        this.statecode = (byte)State.WAITING.getCode();
        this.setDetail(goodsSkuRetVo.getDetail());
        this.setDisabled(goodsSkuRetVo.getDisabled());
        this.setId(goodsSkuRetVo.getId());
        this.setImageUrl(goodsSkuRetVo.getImageUrl());
        this.setName(goodsSkuRetVo.getName());
        this.setGoodsSpuId(goodsSkuRetVo.getGoodsSpu().getId());
        this.setSkuSn(goodsSkuRetVo.getSkuSn());
        this.setOriginalPrice(goodsSkuRetVo.getOriginalPrice());
        this.setConfiguration(goodsSkuRetVo.getConfiguration());
        this.setWeight(goodsSkuRetVo.getWeight());
        this.setInventory(goodsSkuRetVo.getInventory());
        this.setGmtCreate(goodsSkuRetVo.getGmtCreate());
        this.setGmtModified(goodsSkuRetVo.getGmtModified());
    }




}
