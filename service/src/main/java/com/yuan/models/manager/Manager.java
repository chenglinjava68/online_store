package com.yuan.models.manager;

import com.yuan.models.FlagType;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author joryun ON 2017/10/22.
 */
@Entity
@Table(name = "tb_manager")
public class Manager {

    @Id
    @GeneratedValue
    @ApiModelProperty("管理员id")
    private Integer managerId;

    @ApiModelProperty("管理员名字")
    @Column(length = 32)
    @NotBlank
    private String managerName;

    @ApiModelProperty("性别")
    @Column(length = 11)
    private Integer sex;

    @ApiModelProperty("账号")
    @Column(length = 32)
    @NotNull
    private String account;

    @ApiModelProperty("密码")
    @Column(length = 32)
    @NotNull
    private String password;

    @ApiModelProperty("手机号")
    @Column(length = 16)
    private String phone;

    @ApiModelProperty("创建时间")
    @NotNull
    private Date createTime;

    @ApiModelProperty("管理员角色，admin：超级管理员, simple：平台管理员, shop：店铺管理员")
    @Column(length = 4)
    @NotNull
    private RoleType managerRole;

    @ApiModelProperty("是否删除")
    @NotNull
    private FlagType flag;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FlagType getFlag() {
        return flag;
    }

    public void setFlag(FlagType flag) {
        this.flag = flag;
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

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", managerName='" + managerName + '\'' +
                ", sex=" + sex +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", managerRole=" + managerRole +
                ", flag=" + flag +
                '}';
    }
}
