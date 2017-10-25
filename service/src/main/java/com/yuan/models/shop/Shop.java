package com.yuan.models.shop;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author joryun ON 2017/10/25.
 */
@Entity
@Table(name = "tb_shop")
public class Shop {

    @Id
    @GeneratedValue
    @ApiModelProperty("店铺id")
    private Integer shopId;

    @ApiModelProperty("店铺名字")
    private String shopName;


}
