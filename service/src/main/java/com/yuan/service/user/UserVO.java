package com.yuan.service.user;

import com.yuan.models.FlagType;
import com.yuan.models.user.UserType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author joryun ON 2017/10/22.
 */
@Data
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

}
