package com.yuan.service.user;

import com.yuan.models.FlagType;
import com.yuan.models.user.UserType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author joryun ON 2017/10/22.
 */
public class UserVO {

    private Integer userId;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户头像")
    private String headImage;

    @ApiModelProperty("用户性别")
    private Integer sex;

    @ApiModelProperty("用户创建日期")
    private Date createTime;

    @ApiModelProperty("最近登录时间")
    private Date updateTime;

    @ApiModelProperty("用户是否删除")
    private FlagType flag;

    @ApiModelProperty("用户余额")
    private Integer balance;

    @ApiModelProperty("冻结金额")
    private Integer freezeBalance;

    @ApiModelProperty("用户手机号码")
    private String phone;

    @ApiModelProperty("分成余额")
    private Integer dividend;

    @ApiModelProperty("分成比例")
    @NotNull(message = "分成比例不为空")
    private BigDecimal balanceProportion;

    @ApiModelProperty("小区id")
    private Integer districtId;

    @ApiModelProperty("角色，Ordinary：普通用户，District：产品经理")
    private UserType role;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
