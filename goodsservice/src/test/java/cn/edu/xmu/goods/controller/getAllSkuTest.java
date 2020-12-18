package cn.edu.xmu.goods.controller;

import cn.edu.xmu.goods.GoodsServiceApplication;
import cn.edu.xmu.goods.model.vo.UpdateBrandVoBody;
import cn.edu.xmu.ooad.util.JacksonUtil;
import cn.edu.xmu.ooad.util.JwtHelper;
import com.alibaba.fastjson.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yancheng Lai
 * createdBy Yancheng Lai 2020/12/18 15:51
 * modifiedBy Yancheng Lai 15:51
 **/
@SpringBootTest(classes = GoodsServiceApplication.class)   //标识本类是一个SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class getAllSkuTest {
    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceApplication.class);
    @Autowired
    private MockMvc mvc;
    public void deleteGoodsBrandTest1() throws Exception {
        //String token = creatTestToken(1L, 1L, 100);
        String responseString = this.mvc.perform(get("/goods/skus?shopId=1&page=1&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
        String expectedResponse = "{\"errno\":0,\"data\":{\"total\":10,\"pages\":1,\"pageSize\":10,\"page\":1,\"list\":[{\"id\":71,\"name\":\"戴荣华\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":72,\"name\":\"范敏祺\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":73,\"name\":\"黄卖九\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":74,\"name\":\"李进\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":75,\"name\":\"李菊生\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":76,\"name\":\"李小聪\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":77,\"name\":\"刘伟\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":78,\"name\":\"陆如\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":79,\"name\":\"秦锡麟\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"},{\"id\":80,\"name\":\"舒慧娟\",\"imageUrl\":null,\"detail\":null,\"gmtCreate\":\"2020-12-07T11:24:47\",\"gmtModified\":\"2020-12-07T11:24:47\"}]},\"errmsg\":\"成功\"}\n";
        JSONAssert.assertEquals(responseString, expectedResponse, true);
    }
}
