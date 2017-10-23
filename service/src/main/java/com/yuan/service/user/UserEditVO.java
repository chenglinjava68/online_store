package com.yuan.service.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @author joryun ON 2017/10/22.
 */
@ApiModel("产品经理更新信息VO")
public class UserEditVO {

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("用户手机号码")
    @Column(length = 16)
    private String phone;

    @ApiModelProperty("小区id")
    private Integer districtId;

    @ApiModelProperty("分成比例")
    private BigDecimal balanceProportion;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public BigDecimal getBalanceProportion() {
        return balanceProportion;
    }

    public void setBalanceProportion(BigDecimal balanceProportion) {
        this.balanceProportion = balanceProportion;
    }
}
