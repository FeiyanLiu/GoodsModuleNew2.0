package cn.edu.xmu.goods.model.vo;

import cn.edu.xmu.otherservice.model.vo.CustomerVo;

public class CustomerRetVo {
 Long id;
 String userName;
 String realName;
 public CustomerRetVo(CustomerVo customerVo)
 {
     this.id=customerVo.getId();
     this.realName=customerVo.getRealName();
     this.userName=customerVo.getUserName();
 }

}
