package com.yuan.service.manager;

import com.yuan.models.manager.RoleType;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author joryun ON 2017/10/22.
 */
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
    private Integer sex;

    @ApiModelProperty("管理员账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleType getManagerRole() {
        return managerRole;
    }

    public void setManagerRole(RoleType managerRole) {
        this.managerRole = managerRole;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

}
