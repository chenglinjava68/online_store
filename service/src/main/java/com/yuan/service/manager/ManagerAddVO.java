package com.yuan.service.manager;

import com.yuan.models.SexType;
import com.yuan.models.manager.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author joryun ON 2017/10/22.
 */
@Data
public class ManagerAddVO {

    @ApiModelProperty("姓名")
    private String managerName;

    @ApiModelProperty("角色")
    private RoleType managerRole;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("管理员账号")
    private String account;

    @ApiModelProperty("性别")
    private SexType sex;

    @ApiModelProperty("手机号")
    private String phone;

}
