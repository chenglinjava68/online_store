package com.yuan.service.manager;

import com.yuan.models.manager.RoleType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author joryun ON 2017/10/22.
 */
@ApiModel("管理员登录成功后返回信息")
@Data
public class ManagerLoginSuccessVO {

    @ApiModelProperty("管理员姓名")
    private String managerName;

    @ApiModelProperty("管理员角色")
    private RoleType managerRole;

    @ApiModelProperty("管理员id")
    private Integer managerId;

    @ApiModelProperty("管理员标示")
    private String token;

    public ManagerLoginSuccessVO(String managerName, String token) {
        this.managerName = managerName;
        this.token = token;
    }

    public ManagerLoginSuccessVO(String managerName, RoleType managerRole, Integer managerId, String token) {
        this.managerName = managerName;
        this.managerRole = managerRole;
        this.managerId = managerId;
        this.token = token;
    }

}
