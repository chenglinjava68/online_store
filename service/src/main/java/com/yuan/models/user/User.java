package com.yuan.models.user;

import com.yuan.models.FlagType;
import com.yuan.models.SexType;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author joryun ON 2017/10/12.
 */
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue
    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("用户昵称")
    @Column(length = 32)
    private String nickname;

    @ApiModelProperty("真实姓名")
    @Column(length = 32)
    private String realName;

    @ApiModelProperty("用户头像")
    @Column(length = 512)
    private String headImage;

    @ApiModelProperty("用户性别")
    private SexType sex;

    @ApiModelProperty("微信OpenId")
    @Column(length = 256)
    private String openId;

    @ApiModelProperty("用户手机号码")
    @Column(length = 16)
    private String phone;

    @ApiModelProperty("出生年月")
    @Column(length = 16)
    private String birthday;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户余额")
    private Integer balance;

    @ApiModelProperty("冻结金额")
    private Integer freezeBalance;

    @ApiModelProperty("用户创建日期")
    @NotNull
    private Date createTime;

    @ApiModelProperty("最近登录时间")
    @NotNull
    private Date updateTime;

    @ApiModelProperty("用户是否删除")
    private FlagType flag;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(Integer freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public FlagType getFlag() {
        return flag;
    }

    public void setFlag(FlagType flag) {
        this.flag = flag;
    }
}
