package cn.edu.xmu.goods.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    Long id;
    String userName;
    public UserVo(){}

}
