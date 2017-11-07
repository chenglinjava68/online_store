package com.yuan.models.user;

import com.yuan.models.FlagType;
import com.yuan.models.SexType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author joryun ON 2017/10/12.
 */
@Entity
@Table(name = "tb_user")
@Data
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

    @ApiModelProperty("是否删除")
    @NotNull
    private FlagType flag;

}
