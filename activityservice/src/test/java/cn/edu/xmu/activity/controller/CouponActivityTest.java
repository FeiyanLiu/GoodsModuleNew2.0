package cn.edu.xmu.activity.controller;

import cn.edu.xmu.activity.ActivityServiceApplication;
import cn.edu.xmu.activity.model.vo.CouponActivitySimpleVo;
import cn.edu.xmu.activity.model.vo.CouponActivityVo;
import cn.edu.xmu.ooad.util.JacksonUtil;
import cn.edu.xmu.ooad.util.JwtHelper;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Feiyan Liu
 * @date Created at 2020/12/16 22:09
 */
@SpringBootTest(classes = ActivityServiceApplication.class)   //标识本类是一个SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CouponActivityTest {

        @Autowired
        private MockMvc mvc;
        private static final Logger logger = LoggerFactory.getLogger(ActivityServiceApplication.class);

        private final String creatTestToken(Long userId, Long departId, int expireTime) {
            String token = new JwtHelper().createToken(userId, departId, expireTime);
            logger.debug(token);
            return token;
        }

        /**
         * @description:新增优惠活动商品 成功
         * @author: Feiyan Liu
         * @date: Created at 2020/12/3 11:58
         */

//        @Test
//        public void addCouponSku1() throws Exception {
//                String token=creatTestToken(1L, 0L, 100);
//                Long[] skuId= new Long[1];
//                skuId[0]=273L;
//                String requireJson = JacksonUtil.toJson(skuId);
//                String responseString = this.mvc.perform(post("/coupon/shops/0/couponactivities/2158/skus").header("authorization",token)
//                        .contentType("application/json;charset=UTF-8")
//                        .content(requireJson))
//                        .andExpect(status().isOk())
//                        .andReturn().getResponse().getContentAsString();
//
//                String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";
//
//                JSONAssert.assertEquals(expectedResponse, responseString, true);
//        }

//    /**
//     * 活动不存在
//     * @throws Exception
//     */
//    @Test
//    public void addCouponSku2() throws Exception {
//        String token=creatTestToken(1L, 0L, 100);
//        Long[] skuId= new Long[1];
//        skuId[0]=273L;
//        String requireJson = JacksonUtil.toJson(skuId);
//        String responseString = this.mvc.perform(post("/coupon/shops/0/couponactivities/1582222/skus").header("authorization",token)
//                .contentType("application/json;charset=UTF-8")
//                .content(requireJson))
//                .andExpect(status().isNotFound())
//                .andReturn().getResponse().getContentAsString();
//
//        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";
//
//        JSONAssert.assertEquals(expectedResponse, responseString, true);
//    }
//
    /**
     * 商品不存在
     * @throws Exception
     */
//    @Test
//    public void addCouponSku3() throws Exception {
//        String token=creatTestToken(1L, 0L, 100);
//        Long[] skuId= new Long[1];
//        skuId[0]=27773L;
//        String requireJson = JacksonUtil.toJson(skuId);
//        String responseString = this.mvc.perform(post("/coupon/shops/0/couponactivities/2158/skus").header("authorization",token)
//                .contentType("application/json;charset=UTF-8")
//                .content(requireJson))
//                .andExpect(status().isNotFound())
//                .andReturn().getResponse().getContentAsString();
//
//        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";
//
//        JSONAssert.assertEquals(expectedResponse, responseString, true);
//    }
//
//    /**
//     * 活动已被删除
//     * @throws Exception
//     */
//    @Test
//    public void addCouponSku4() throws Exception {
////        String token=creatTestToken(1L, 0L, 100);
////        Long[] skuId= new Long[1];
////        skuId[0]=273L;
////        String requireJson = JacksonUtil.toJson(skuId);
////        String responseString = this.mvc.perform(post("/coupon/shops/0/couponactivities/5821/skus").header("authorization",token)
////                .contentType("application/json;charset=UTF-8")
////                .content(requireJson))
////                .andExpect(status().isForbidden())
////                .andReturn().getResponse().getContentAsString();
////
////        String expectedResponse = "{\"errno\":904,\"errmsg\":\"优惠活动状态禁止\"}";
////
////        JSONAssert.assertEquals(expectedResponse, responseString, true);
////    }
//
//    /**
//     * 商品不属于本店
//     * @throws Exception
//     */
//    @Test
//    public void addCouponSku5() throws Exception {
//        String token=creatTestToken(1L, 0L, 100);
//        Long[] skuId= new Long[1];
//        skuId[0]=1275L;
//        String requireJson = JacksonUtil.toJson(skuId);
//        String responseString = this.mvc.perform(post("/coupon/shops/0/couponactivities/2158/skus").header("authorization",token)
//                .contentType("application/json;charset=UTF-8")
//                .content(requireJson))
//                .andExpect(status().isForbidden())
//                .andReturn().getResponse().getContentAsString();
//
//        String expectedResponse = "{\"errno\":505,\"errmsg\":\"操作的资源id不是自己的对象\"}";
//
//        JSONAssert.assertEquals(expectedResponse, responseString, true);
//    }

    /**
     * 删除优惠活动中的商品 操作的活动不是自己的对象
     * @throws Exception
     */
    @Test
    public void deleteCouponSku1() throws Exception {
        String token=creatTestToken(1L, 2L, 100);
        String responseString = this.mvc.perform(delete("/coupon/shops/2/couponskus/1").header("authorization",token)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":505,\"errmsg\":\"操作的资源id不是自己的对象\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

//
//    /**
//     * 删除优惠活动中的商品 操作的商品不是自己的对象
//     * @throws Exception
//     */
//    @Test
//    public void deleteCouponSku3() throws Exception {
//        String token=creatTestToken(1L, 0L, 100);
//        String responseString = this.mvc.perform(delete("/coupon/shops/0/couponactivities/1235/skus").header("authorization",token)
//                .contentType("application/json;charset=UTF-8"))
//                .andExpect(status().isForbidden())
//                .andReturn().getResponse().getContentAsString();
//        String expectedResponse = "{\"errno\":505,\"errmsg\":\"操作的资源id不是自己的对象\"}";
//
//        JSONAssert.assertEquals(expectedResponse, responseString, true);
//    }
//
//    /**
//     * 删除优惠活动中的商品 coupon_sku的id不存在
//     * @throws Exception
//     */
//    @Test
//    public void deleteCouponSku2() throws Exception {
//        String token=creatTestToken(1L, 0L, 100);
//        String responseString = this.mvc.perform(delete("/coupon/shops/2/couponskus/1").header("authorization",token)
//                .contentType("application/json;charset=UTF-8"))
//                .andExpect(status().isNotFound())
//                .andReturn().getResponse().getContentAsString();
//
//        String expectedResponse ="{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";
//
//        JSONAssert.assertEquals(expectedResponse, responseString, true);
//    }

    /**
     * 删除优惠活动 操作的活动不是自己的对象
     * @throws Exception
     */
    @Test
    public void deleteCouponActivity1() throws Exception {
        String token=creatTestToken(1L, 0L, 100);
        String responseString = this.mvc.perform(delete("/coupon/shops/0/couponactivities/1582").header("authorization",token)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isForbidden())
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":505,\"errmsg\":\"操作的资源id不是自己的对象\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void getCouponActivity1() throws Exception {
        String token=creatTestToken(1L, 0L, 100);
        String responseString = this.mvc.perform(get("/coupon/shops/0/couponactivities/100415648632").header("authorization",token)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isNotFound())
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void getInvalidCouponActivity1() throws Exception {
        String token=creatTestToken(1L, 0L, 100);
        String responseString = this.mvc.perform(get("/coupon/shops/0/couponactivities/invalid?page=1&pageSize=4").header("authorization",token)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\n" +
                "  \"errno\": 0,\n" +
                "  \"data\": {\n" +
                "    \"total\": 4,\n" +
                "    \"pages\": 1,\n" +
//                "    \"pageSize\": 10,\n" +
//                "    \"page\": 1,\n" +
                "    \"list\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"string\",\n" +
                "        \"beginTime\": \"2022-12-13T08:02:14\",\n" +
                "        \"endTime\": \"2024-12-13T08:02:14\",\n" +
                "        \"couponTime\": \"2022-12-13T08:02:14\",\n" +
                "        \"quantity\": 0,\n" +
                "        \"imageUrl\": null\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"string\",\n" +
                "        \"beginTime\": \"2022-12-13T08:02:14\",\n" +
                "        \"endTime\": \"2024-12-13T08:02:14\",\n" +
                "        \"couponTime\": \"2022-12-13T08:02:14\",\n" +
                "        \"quantity\": 0,\n" +
                "        \"imageUrl\": null\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 9,\n" +
                "        \"name\": \"string\",\n" +
                "        \"beginTime\": \"2020-12-13T16:33:50\",\n" +
                "        \"endTime\": \"2021-12-29T08:28:50\",\n" +
                "        \"couponTime\": \"2020-12-14T08:28:50\",\n" +
                "        \"quantity\": 0,\n" +
                "        \"imageUrl\": null\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2158,\n" +
                "        \"name\": \"双十一\",\n" +
                "        \"beginTime\": \"2021-01-20T22:46:38\",\n" +
                "        \"endTime\": \"2021-01-30T22:46:55\",\n" +
                "        \"couponTime\": \"2021-01-10T22:47:01\",\n" +
                "        \"quantity\": 0,\n" +
                "        \"imageUrl\": null\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "  \"errmsg\": \"成功\"\n" +
                "}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);
    }

//
//        /**
//         * @description:shopId和departId不符合 无权限新建活动
//         * @author: Feiyan Liu
//         * @date: Created at 2020/12/3 12:00
//         */
        @Test
        public void addCouponActivity2() throws Exception {
            String token=creatTestToken(1L, 1L, 100);
            String json="{\n" +
                    "  \"beginTime\": \"2022-12-15T07:05:33.976Z\",\n" +
                    "  \"couponTime\": \"2022-12-15T07:05:33.976Z\",\n" +
                    "  \"endTime\": \"2023-12-15T07:05:33.976Z\",\n" +
                    "  \"name\": \"string\",\n" +
                    "  \"quantityType\": 0,\n" +
                    "  \"quantity\": 0,\n" +
                    "  \"strategy\": \"string\",\n" +
                    "  \"validTerm\": 0\n" +
                    "}";

            String responseString = this.mvc.perform(post("/coupon/shops/1/couponactivities").header("authorization",token)
                    .contentType("application/json;charset=UTF-8")
                    .content(json))
                    .andExpect(status().isForbidden())
                    .andReturn().getResponse().getContentAsString();
        }
//        /**
//         * @description: 添加到优惠活动的商品非本店铺的
//         * @author: Feiyan Liu
//         * @date: Created at 2020/12/3 12:15
//         */
//        @Test
//        public void addCouponActivity3() throws Exception {
//            String token=creatTestToken(1L, 2L, 100);
//            CouponActivityVo vo=new CouponActivityVo();
//            vo.setName("618大促");
//            vo.setQuantity(1);
//            vo.setQuantityType((byte) 0);
//            vo.setStrategy("1");
//            vo.setValidTerm((byte) 0);
//
//            String requireJson = JacksonUtil.toJson(vo);
//
//            String responseString = this.mvc.perform(post("/coupon/shops/2/Skus/273/couponactivities").header("authorization",token)
//                    .contentType("application/json;charset=UTF-8")
//                    .content(requireJson))
//                    .andExpect(status().isOk())
//                    .andExpect(content().contentType("application/json;charset=UTF-8"))
//                    .andReturn().getResponse().getContentAsString();
//
//            String expectedResponse= "{\"errno\":505,\"errmsg\":\"创建优惠活动失败，商品非用户店铺的商品\"}";
//            JSONAssert.assertEquals(expectedResponse, responseString, true);
//        }
/////**
//// * @description: 商品在同时间段内已经参与了其他活动
//// * @return:
//// * @author: Feiyan Liu
//// * @date: Created at 2020/12/3 13:39
//// */
////
////    @Test
////    public void addCouponActivity4() throws Exception {
////        String token=creatTestToken(1L, 2L, 100);
////        CouponActivityVo vo=new CouponActivityVo();
////        vo.setName("618大促");
////        vo.setQuantity(1);
////        vo.setQuantityType((byte) 0);
////        vo.setStrategy("1");
////        vo.setValidTerm((byte) 0);
////
////        String requireJson = JacksonUtil.toJson(vo);
////
////        String responseString = this.mvc.perform(post("/coupon/shops/2/skus/273/couponactivities").header("authorization",token)
////                .contentType("application/json;charset=UTF-8")
////                .content(requireJson))
////                .andExpect(status().isOk())
////                .andExpect(content().contentType("application/json;charset=UTF-8"))
////                .andReturn().getResponse().getContentAsString();
////
////        String expectedResponse= "{\"errno\":505,\"errmsg\":\"创建优惠活动失败，商品非用户店铺的商品\"}";
////        JSONAssert.assertEquals(expectedResponse, responseString, true);
////    }

//    /**
////     * 活动已被删除
////     * @throws Exception
////     */
    @Test
    public void getCoupon() throws Exception {
        String token=creatTestToken(14513111210L, 0L, 100);
        String responseString = this.mvc.perform(post("/coupon/couponactivities/12158/usercoupons").header("authorization",token)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }
    /**
     //     * 活动已被删除
     //     * @throws Exception
     //     */
    @Test
    public void updateCouponActivity() throws Exception {
        CouponActivitySimpleVo vo=new CouponActivitySimpleVo();
        vo.setBeginTime(LocalDateTime.now());
        vo.setEndTime(LocalDateTime.now().plusDays(100));
        vo.setName("618大促");
        vo.setQuantity(0);
        vo.setQuantityType((byte)0);
        vo.setStrategy("优惠策略");
        String json= JacksonUtil.toJson(vo);

        String token=creatTestToken(1L, 0L, 100);
        String responseString = this.mvc.perform(put("/coupon/shops/0/couponactivities/2158").header("authorization",token)
                .contentType("application/json;charset=UTF-8").content(json))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);
    }
    }

