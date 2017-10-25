package com.yuan.service.manager;

import com.yuan.models.SexType;
import com.yuan.models.manager.RoleType;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author joryun ON 2017/10/22.
 */
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

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public RoleType getManagerRole() {
        return managerRole;
    }

    public void setManagerRole(RoleType managerRole) {
        this.managerRole = managerRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
