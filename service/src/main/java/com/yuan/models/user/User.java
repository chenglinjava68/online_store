package com.yuan.models.user;

import com.yuan.models.FlagType;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author joryun ON 2017/10/12.
 */
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue
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
    @Column(length = 4)
    private Integer sex;

    @ApiModelProperty("用户创建日期")
    @NotNull
    private Date createTime;

    @ApiModelProperty("最近登录时间")
    @NotNull
    private Date updateTime;

    @ApiModelProperty("微信OpenId")
    @Column(length = 256)
    private String openId;

    @ApiModelProperty("用户手机号码")
    @Column(length = 16)
    private String phone;

    @ApiModelProperty("出生年月")
    @Column(length = 16)
    private String birthday;

    @ApiModelProperty("用户余额")
    private Integer balance;

    @ApiModelProperty("冻结金额")
    private Integer freezeBalance;

    //产品经理属性
    @ApiModelProperty("分成余额")
    private Integer dividend;

    @ApiModelProperty("分成比例")
    private BigDecimal balanceProportion;

    @ApiModelProperty("小区id")
    private Integer districtId;

    @ApiModelProperty("角色，Ordinary：普通用户，District：产品经理")
    private UserType role;

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

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public FlagType getFlag() {
        return flag;
    }

    public void setFlag(FlagType flag) {
        this.flag = flag;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getDividend() {
        return dividend;
    }

    public void setDividend(Integer dividend) {
        this.dividend = dividend;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public BigDecimal getBalanceProportion() {
        return balanceProportion;
    }

    public void setBalanceProportion(BigDecimal balanceProportion) {
        this.balanceProportion = balanceProportion;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", realName='" + realName + '\'' +
                ", headImage='" + headImage + '\'' +
                ", sex=" + sex +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", openId='" + openId + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", balance=" + balance +
                ", freezeBalance=" + freezeBalance +
                ", dividend=" + dividend +
                ", balanceProportion=" + balanceProportion +
                ", districtId=" + districtId +
                ", role=" + role +
                ", flag=" + flag +
                '}';
    }
}
