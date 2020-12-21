package cn.edu.xmu.provider.service.impl;


import cn.edu.xmu.ooad.util.ReturnObject;
import cn.edu.xmu.privilegeservice.client.IUserService;
import cn.edu.xmu.privilegeservice.model.bo.User;
import cn.edu.xmu.privilegeservice.model.po.UserPo;
import cn.edu.xmu.provider.dao.UserDao;
import com.alibaba.nacos.api.common.ResponseCode;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Yifei Wang 24320182203286
 * @Date: 2020/12/10 9:43
 */

@DubboService(version = "0.0.1-SNAPSHOT")
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean changeUserDepart(Long userId, Long departId) {
        UserPo po=userDao.findUserById(userId);
        if(po == null){
            return false;
        }
        if(po.getDepartId() != -1){
            return false;
        }
        ReturnObject ret=userDao.changeUserDepart(userId, departId);
        if(ret.getCode().equals(ResponseCode.OK)){
            return false;
        }
        return true;
    }

    @Override
    public String getUserName(Long userId) {
        ReturnObject<User> ret = userDao.getUserById(userId);
        if(ret.getCode().equals(ResponseCode.OK)){
            return null;
        }
        User user = ret.getData();
        if(user.getState() == User.State.DELETE || user.getState() == User.State.FORBID){
            return null;
        }
        return ret.getData().getUserName();
    }
}
