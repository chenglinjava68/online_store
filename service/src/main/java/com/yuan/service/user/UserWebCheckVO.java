package com.yuan.service.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author joryun ON 2017/10/22.
 */
@ApiModel("用户是否已绑定手机VO")
@Data
public class UserWebCheckVO {

    @ApiModelProperty("是否已绑定手机。0：未绑定，1：已绑定")
    private Integer hasBind;

    @ApiModelProperty("手机号码")
    private String phone;

    public UserWebCheckVO(Integer hasBind) {
        this.hasBind = hasBind;
    }

    public UserWebCheckVO(Integer hasBind, String phone) {
        this.hasBind = hasBind;
        this.phone = phone;
    }

}
