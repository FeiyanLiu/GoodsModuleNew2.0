package cn.edu.xmu.goods.service;

import cn.edu.xmu.goods.dao.GoodsCategoryDao;
import cn.edu.xmu.goods.dao.GoodsSpuDao;
import cn.edu.xmu.goods.model.bo.GoodsCategory;
import cn.edu.xmu.goods.model.po.GoodsCategoryPo;
import cn.edu.xmu.goods.model.po.GoodsSkuPo;
import cn.edu.xmu.goods.model.po.GoodsSpuPo;
import cn.edu.xmu.goods.model.vo.GoodsCategoryRetVo;
import cn.edu.xmu.goods.model.vo.GoodsCategorySimpleVo;
import cn.edu.xmu.ooad.model.VoObject;
import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author：谢沛辰
 * @Date: 2020.11.30
 * @Description:商品类型业务逻辑
 */
@Service
public class GoodsCategoryService{
    private Logger logger = LoggerFactory.getLogger(GoodsCategoryService.class);

    @Autowired
    GoodsCategoryDao goodsCategoryDao;

    @Autowired
    GoodsSpuDao goodsSpuDao;

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: long pid
     * @Return: ReturnObject<List>
     * @Description:查找子类别
     */
    @Transactional
    public ReturnObject<List<GoodsCategoryRetVo>> findSubCategory(long pid){

        List<GoodsCategoryPo> lst = goodsCategoryDao.getCategoryByPID(pid).getData();
        List<GoodsCategoryRetVo> ret = new ArrayList<>();
        if(pid != 0){
            ReturnObject<GoodsCategory> fatherCategory = goodsCategoryDao.getCategoryById(pid);
            if(fatherCategory == null || fatherCategory.getCode()==ResponseCode.RESOURCE_ID_NOTEXIST){
                return new ReturnObject<>(ResponseCode.RESOURCE_ID_NOTEXIST);
            }

        }

        if(lst != null && lst.size() !=0){
            for(GoodsCategoryPo goodsCategoryPo : lst){
                ret.add(new GoodsCategory(goodsCategoryPo).createVo());
            }
        } else {

        }
        return new ReturnObject<>(ret);
    }

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: long pid, String name
     * @Return: ReturnObject<GoodsCategoryRetVo>
     * @Description:创建类别
     */
    @Transactional
    public ReturnObject<GoodsCategoryRetVo> createCategory(GoodsCategoryRetVo vo){
        GoodsCategory goodsCategory=new GoodsCategory();
        goodsCategory.setPid(vo.getPid());
        goodsCategory.setName(vo.getName());
        goodsCategory.setGmtGreate(LocalDateTime.now());
        goodsCategory.setGmtModified(LocalDateTime.now());
        ReturnObject<GoodsCategoryPo> media=goodsCategoryDao.insertSubcategory(goodsCategory);
        ReturnObject<GoodsCategoryRetVo> result=null;



        if(media.getCode()==ResponseCode.OK)
        {
            GoodsCategory receiver=new GoodsCategory(media.getData());
            result= new ReturnObject<>(receiver.createVo());
        }
        else
        {
            result=new ReturnObject<>(media.getCode());
        }
        return result;
    }

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: long id,String name
     * @Return: ReturnObject<Object>
     * @Description: 更新类别信息
     */
    @Transactional
    public ReturnObject updateCategory(GoodsCategoryRetVo vo){
        ReturnObject<GoodsCategory> targetCategory=goodsCategoryDao.getCategoryById(vo.getId());
        if(targetCategory.getData()!=null){
            GoodsCategory selected= targetCategory.getData();
            selected.setGmtModified(LocalDateTime.now());
            selected.setName(vo.getName());
            return goodsCategoryDao.updateCategory(selected);
        }else{
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
    }

    /**
     * @Author：谢沛辰
     * @Date: 2020/12/8
      * @Param: long id
     * @Return: ReturnObject<Object>
     * @Description: 删除类别
     */

    @Transactional
    public ReturnObject<Object> deleteCategory(long id){
        ReturnObject<Object> returnObject = goodsSpuDao.setCategoryIdDefault(id,0l);
        return goodsCategoryDao.deleteCategory(id);
    }

    @Transactional
    public ReturnObject<GoodsCategorySimpleVo> findCategorySimpleVoById(Long categoryId){
        ReturnObject<GoodsCategory> ret = goodsCategoryDao.getCategoryById(categoryId);
        return new ReturnObject<>(ret.getData().createSimpleVo());
    }
}
