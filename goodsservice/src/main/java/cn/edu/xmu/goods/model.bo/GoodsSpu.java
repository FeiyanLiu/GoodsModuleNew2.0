package cn.edu.xmu.goods.model.bo;

import cn.edu.xmu.goods.model.po.GoodsSpuPo;
import cn.edu.xmu.goods.model.vo.*;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.ooad.util.JacksonUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/** 
* @Description: 商品SKU Bo
* @Author: Yancheng Lai
* @Date: 2020/12/1 20:08
*/



@Data
public class GoodsSpu implements VoObject, Serializable {


    String name;
    Long weight;
    String imageUrl;
    Integer inventory;
    String detail;
    LocalDateTime gmtCreate;
    LocalDateTime gmtModified;
    Byte disabled;
    Long brandId;
    Long categoryId;
    Long freightId;
    Long shopId;
    String spec;
    String goodsSn;
    Long id;
    /**
    * @Description:  构造函数byPo
    * @Param: [goodsSpuPo]
    * @return:  GoodsSpu
    * @Author: Yancheng Lai
    * @Date: 2020/12/1 19:51
    */
    public GoodsSpu(GoodsSpuPo goodsSpuPo) {
        this.name=goodsSpuPo.getName();
    }

    public GoodsSpu(GoodsSpuVo goodsSpuVo) {
        this.setName(goodsSpuVo.getName());
        this.setDetail(goodsSpuVo.getDescription());
        this.setSpec(goodsSpuVo.getSpecs());
    }

    //注意 注意 由于集成的历史原因 这里的FreightModel还没有初始化
    @Override
    public GoodsSpuRetVo createVo() {
           return new GoodsSpuRetVo(this);
    }




    @Override
    public GoodsSpuSimpleRetVo createSimpleVo() {
        return new GoodsSpuSimpleRetVo(this);
    }


    //Spec转换还要做
    public GoodsSpu(GoodsSpuRetVo goodsSpuRetVo){
        this.setBrandId(goodsSpuRetVo.getBrand().getId());
        this.setCategoryId(goodsSpuRetVo.getCategory().getId());
        this.setDetail(goodsSpuRetVo.getDetail());
        this.setDisabled(goodsSpuRetVo.getDisabled());
        this.setFreightId(goodsSpuRetVo.getFreightId());
        this.setId(goodsSpuRetVo.getId());
        this.setImageUrl(goodsSpuRetVo.getImageUrl());
        this.setName(goodsSpuRetVo.getName());
        this.setShopId(goodsSpuRetVo.getShop().getId());
        this.setSpec(JacksonUtil.toJson(goodsSpuRetVo.getSpec()));
        this.setGoodsSn(goodsSpuRetVo.getGoodsSn());
        this.setGmtCreate(goodsSpuRetVo.getGmtCreate());
        this.setGmtModified(goodsSpuRetVo.getGmtModified());
    }



}
