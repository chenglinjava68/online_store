package com.yuan.service.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author joryun ON 2017/10/22.
 */
@ApiModel("客户端用户绑定手机model")
public class UserWebBindPhoneVO {

    @ApiModelProperty("手机号码")
    @NotBlank(message = "手机号不能为空")
    private String mobilePhone;

    @ApiModelProperty("验证码")
    @NotBlank(message = "验证码不能为空")
    private String verCode;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

}
