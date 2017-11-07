package com.yuan.models.shop;

import com.yuan.models.FlagType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author joryun ON 2017/10/25.
 */
@Entity
@Table(name = "tb_shop")
@Data
public class Shop {

    @Id
    @GeneratedValue
    @ApiModelProperty("店铺id")
    private Integer shopId;

    @ApiModelProperty("店铺名字")
    private String name;

    @ApiModelProperty("管理员id")
    @NotNull
    private Integer managerId;

    @ApiModelProperty("用户创建日期")
    @NotNull
    private Date createTime;

    @ApiModelProperty("是否删除")
    @NotNull
    private FlagType flag;
}
