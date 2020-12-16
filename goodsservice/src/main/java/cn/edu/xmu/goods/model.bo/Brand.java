package cn.edu.xmu.goods.model.bo;

import cn.edu.xmu.goods.model.po.BrandPo;
import cn.edu.xmu.goods.model.vo.BrandRetVo;
import cn.edu.xmu.goods.model.vo.BrandSimpleRetVo;
import cn.edu.xmu.goods.model.vo.UpdateBrandVoBody;
import cn.edu.xmu.ooad.model.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 品牌
 * @author Yancheng Lai
 * createdBy Yancheng Lai 2020/12/2 16:56
 * modifiedBy Yancheng Lai 16:56
 **/
@Data
public class Brand implements VoObject{

    private Long id;

    private String name;

    private String imageUrl;

    private String detail;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    //private BrandPo brandPo;

    public Brand(BrandPo brandPo){
        setGmtCreate(brandPo.getGmtCreate());
        setGmtModified(brandPo.getGmtModified());
        setName(brandPo.getName());
        setImageUrl(brandPo.getImageUrl());
        setDetail(brandPo.getDetail());;
        setId(brandPo.getId());
    }

    public Brand(UpdateBrandVoBody vo) {
        setDetail(vo.getDetail());
        setName(vo.getName());
    }

    public BrandPo getBrandPo(){
        BrandPo brandPo = new BrandPo();
        brandPo.setGmtCreate(getGmtCreate());
        brandPo.setGmtModified(getGmtModified());
        brandPo.setName(getName());
        brandPo.setImageUrl(getImageUrl());
        brandPo.setDetail(getDetail());;
        brandPo.setId(getId());
        return brandPo;
    }

    public Brand(BrandRetVo brandRetVo){
        setDetail(brandRetVo.getDetail());
        setId(brandRetVo.getId());
        setImageUrl(brandRetVo.getImageUrl());
        setName(brandRetVo.getName());
        setGmtCreate(brandRetVo.getGmtCreate());
        setGmtModified(brandRetVo.getGmtModified());
    }

    @Override
    public BrandRetVo createVo() {
        return new BrandRetVo(this);
    }

    @Override
    public BrandSimpleRetVo createSimpleVo() {
        return new BrandSimpleRetVo(this);
    }


}
