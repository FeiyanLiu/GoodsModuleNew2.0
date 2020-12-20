package cn.edu.xmu.goods.controller;

import cn.edu.xmu.goods.GoodsServiceApplication;
import cn.edu.xmu.ooad.util.JwtHelper;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yancheng Lai
 * createdBy Yancheng Lai 2020/12/17 20:45
 * modifiedBy Yancheng Lai 20:45
 **/
@SpringBootTest(classes = GoodsServiceApplication.class)   //标识本类是一个SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CommentTest {
    @Autowired
    private MockMvc mvc;

    //private static final Logger logger = LoggerFactory.getLogger(GoodsServiceControllerTest4.class);

    private final String creatTestToken(Long userId, Long departId, int expireTime) {
        String token = new JwtHelper().createToken(userId, departId, expireTime);
        return token;
    }


    @Test
    public void getCommentByUserId() throws Exception {

        String responseString = this.mvc.perform(get("/comment/comments?page=1&pageSize=10"))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();

    }

    @Test
    public void auditComment() throws Exception {
        String json="{\"conclusion\":true}";
        String responseString = this.mvc.perform(put("/comment/shops/0/comments/1111111/confirm")
                .contentType("application/json;charset=UTF-8")
                .content(json))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();
    }
}
