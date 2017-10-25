package com.yuan.service.manager;

import com.yuan.models.SexType;
import com.yuan.models.manager.RoleType;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author joryun ON 2017/10/22.
 */
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

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
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

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
