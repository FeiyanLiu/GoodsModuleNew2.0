package cn.edu.xmu.provider.dao;


import cn.edu.xmu.ooad.util.ResponseCode;
import cn.edu.xmu.ooad.util.ReturnObject;
import cn.edu.xmu.privilegeservice.model.bo.User;
import cn.edu.xmu.privilegeservice.model.po.UserPo;
import cn.edu.xmu.privilegeservice.model.po.UserPoExample;
import cn.edu.xmu.provider.mapper.UserPoMapper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Ming Qiu
 * @date Created in 2020/11/1 11:48
 * Modified in 2020/11/8 0:57
 **/
@Repository
public class UserDao{

    @Autowired
    private UserPoMapper userPoMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    /**
     * 获得用户
     *
     * @param id userID
     * @return User
     * createdBy 3218 2020/11/4 15:48
     * modifiedBy 3218 2020/11/4 15:48
     */

    public ReturnObject<User> getUserById(Long id) {
        UserPo userPo = userPoMapper.selectByPrimaryKey(id);
        if (userPo == null) {
            return new ReturnObject(ResponseCode.RESOURCE_ID_NOTEXIST);
        }
        User user = new User(userPo);
        if (!user.authetic()) {
            StringBuilder message = new StringBuilder().append("getUserById: ").append(ResponseCode.RESOURCE_FALSIFY.getMessage()).append(" id = ")
                    .append(user.getId()).append(" username =").append(user.getUserName());
            logger.error(message.toString());
            return new ReturnObject<>(ResponseCode.RESOURCE_FALSIFY);
        }
        return new ReturnObject<>(user);
    }



    /**
     * ID获取用户信息
     * @author XQChen

     * @return 用户
     */
    public UserPo findUserById(Long Id) {
        UserPoExample example = new UserPoExample();
        UserPoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(Id);

        logger.debug("findUserById: Id =" + Id);
        UserPo userPo = userPoMapper.selectByPrimaryKey(Id);

        return userPo;
    }


    /**
     * 功能描述: 修改用户depart
     * @Param: userId departId
     * @Return:
     * @Author: Yifei Wang
     * @Date: 2020/12/8 11:35
     */
    public ReturnObject changeUserDepart(Long userId, Long departId){
        UserPo po = new UserPo();
        po.setId(userId);
        po.setDepartId(departId);
        try{
            logger.debug("Update User: " + userId);
            int ret=userPoMapper.updateByPrimaryKeySelective(po);
            if(ret == 0){
                return new ReturnObject<>(ResponseCode.FIELD_NOTVALID);
            }
            logger.debug("Success Update User: " + userId);
            return new ReturnObject<>(ResponseCode.OK);
        }catch (Exception e){
            logger.error("exception : " + e.getMessage());
            return new ReturnObject<>(ResponseCode.INTERNAL_SERVER_ERR);
        }
    }
}

