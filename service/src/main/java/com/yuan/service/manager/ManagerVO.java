package com.yuan.service.manager;

import com.yuan.models.SexType;
import com.yuan.models.manager.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author joryun ON 2017/10/22.
 */
@Data
public class ManagerVO {

    @ApiModelProperty("管理员id")
    private Integer managerId;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("姓名")
    private String managerName;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("角色")
    private RoleType managerRole;

    @ApiModelProperty("性别")
    private SexType sex;

}
