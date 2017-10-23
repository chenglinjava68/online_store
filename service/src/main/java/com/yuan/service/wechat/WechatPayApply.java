package com.yuan.service.wechat;

/**
 * @author joryun ON 2017/10/22.
 */
public class WechatPayApply {

    private String appId;               //公众账号ID
    private String mchId;               //商户号
    private String nonceStr;            //随机字符串
    private String sign;                //签名
    private String spbillCreateIp;      //终端IP
    private String outTradeNo;          //商户订单号
    private String totalFee;            //金额
    private String notifyUrl;           //这里notify_url是支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等
    private String body;                //商品描述根据情况修改
    private String openId;              //微信用户对一个公众号唯一
    private String tradeType;           //交易类型
    private String attch;               //附加数据原样返回

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getAttch() {
        return attch;
    }

    public void setAttch(String attch) {
        this.attch = attch;
    }

    @Override
    public String toString() {
        return "WeixinPayApply [appId=" + appId + ", mchId=" + mchId
                + ", nonceStr=" + nonceStr + ", sign=" + sign
                + ", spbillCreateIp=" + spbillCreateIp + ", outTradeNo="
                + outTradeNo + ", totalFee=" + totalFee + ", notifyUrl="
                + notifyUrl + ", body=" + body + ", openId=" + openId
                + ", tradeType=" + tradeType + ", attch=" + attch + "]";
    }
}
