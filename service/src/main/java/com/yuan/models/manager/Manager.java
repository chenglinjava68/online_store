package com.yuan.models.manager;

import com.yuan.models.FlagType;
import com.yuan.models.SexType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author joryun ON 2017/10/22.
 */
@Entity
@Table(name = "tb_manager")
@Data
public class Manager {

    @Id
    @GeneratedValue
    @ApiModelProperty("管理员id")
    private Integer managerId;

    @ApiModelProperty("管理员名字")
    @NotBlank
    private String managerName;

    @ApiModelProperty("性别")
    private SexType sex;

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
    @NotNull
    private RoleType managerRole;

    @ApiModelProperty("是否删除")
    @NotNull
    private FlagType flag;

}
