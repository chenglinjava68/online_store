package com.yuan.service.wechat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author joryun ON 2017/10/22.
 */
public class WechatPayPackage {

    private String appId;

    private String packages;

    private String timestamp;

    private String nonceStr;

    private String signType;

    private String paySign;

    @ApiModelProperty("余额是否足够支付，true：足够，false：不足够")
    private boolean isSuccess;

    public WechatPayPackage() {
    }

    public WechatPayPackage(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return "WechatPayPackage{" +
                "appId='" + appId + '\'' +
                ", packages='" + packages + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
