package com.yuan.service.manager;

import com.yuan.models.SexType;
import com.yuan.models.manager.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author joryun ON 2017/10/22.
 */
@Data
public class ManagerEditVO {

    @ApiModelProperty("管理员id")
    @NotNull
    private Integer managerId;

    @ApiModelProperty("姓名")
    @NotBlank
    private String managerName;

    @ApiModelProperty("电话")
    @NotBlank
    private String phone;

    @ApiModelProperty("角色")
    @NotNull
    private RoleType managerRole;

    @ApiModelProperty("性别")
    private SexType sex;

    @ApiModelProperty("管理员账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

}
