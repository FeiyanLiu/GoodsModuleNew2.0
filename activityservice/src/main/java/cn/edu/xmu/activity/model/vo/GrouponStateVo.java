<<<<<<< HEAD
package cn.edu.xmu.activity.model.vo;

import cn.edu.xmu.activity.model.bo.Groupon;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

/**
 * @author LJP_3424
 * @create 2020-12-01 3:49
 */
@Data
public class GrouponStateVo {
    private Long Code;

    private String name;

    public GrouponStateVo(Groupon.State state) {
        Code = Long.valueOf(state.getCode());
        name = state.getDescription();
    }


}

=======
package cn.edu.xmu.activity.model.vo;

import cn.edu.xmu.activity.model.bo.Groupon;
import cn.edu.xmu.ooad.model.VoObject;
import lombok.Data;

/**
 * @author LJP_3424
 * @create 2020-12-01 3:49
 */
@Data
public class GrouponStateVo {
    private Long Code;

    private String name;

    public GrouponStateVo(Groupon.State state) {
        Code = Long.valueOf(state.getCode());
        name = state.getDescription();
    }


}

>>>>>>> 8b6f493691a74d7b21e5f442f0e456c2b176c239
