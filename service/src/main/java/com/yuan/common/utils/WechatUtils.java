package com.yuan.common.utils;

import com.yuan.common.constants.WechatConstant;
import com.yuan.common.exception.MessageCodes;
import com.yuan.common.exception.ValidationException;
import com.yuan.service.wechat.WechatPayApply;
import com.yuan.service.wechat.WechatPayPackage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author joryun ON 2017/10/22.
 */
@Component
public class WechatUtils {

    private static final Logger logger = LoggerFactory.getLogger(WechatUtils.class);

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Value("${wechat.mchKey}")
    private String mchKey;

    @Value("${wechat.mchId}")
    private String mchId;

    //订单生成的ip
    @Value("${wechat.spbillCreateIp}")
    private String spbillCreateIp;

//-------------------------------------------------------微信登录授权，获取用户信息开始---------------------------------------------

    /**
     * 利用code 和 state 获取 access_token 和 open_id
     */
    public Map<String, Object> authorize(String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appId);
        params.put("secret", appSecret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
        return getWechatReturn(params, url);
    }

    /**
     * 通过 accessToken 和 openId获取用户个人信息
     */
    public Map<String, Object> getUserInfo(String accessToken, String openId) {
        String url = "https://api.weixin.qq.com/sns/userinfo";
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.put("openid", openId);
        params.put("lang", "zh-CN");
        return getWechatReturn(params, url);
    }

//--------------------------------------微信登录授权，获取用户信息结束------------------------------------------------------------


//--------------------------------------刷新调用微信接口的access_token开始---------------------------------------------------

    /**
     * 获取accesstoken,每小时获取一次
     */
    //@Scheduled(cron="0 0 0/1 * * ?")
    public void getToken() {
        logger.info("-----------111111111111111111---------------------");
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", appId);
        params.put("secret", appSecret);
        Map<String, Object> tokenMap = getWechatReturn(params, url);
        WechatConstant.TOKEN = (String) tokenMap.get("access_token");
        Map<String, Object> ticketMap = getTicket(WechatConstant.TOKEN);
        WechatConstant.TICKET = (String) ticketMap.get("ticket");
        logger.info("-------------------------token:{}-----------------", WechatConstant.TOKEN);
        logger.info("-------------------------ticket:{}-----------------", WechatConstant.TICKET);
    }
//--------------------------------------刷新调用微信接口的access_token结束---------------------------------------------------


//--------------------------------------刷新调用微信接口的票据ticket开始---------------------------------------------------

    /**
     * 获得 js_api ticket
     */
    public Map<String, Object> getTicket(String token) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
        Map<String, Object> params = new HashMap<>();
        params.put("type", "jsapi");
        params.put("access_token", token);
        return getWechatReturn(params, url);
    }

//--------------------------------------刷新调用微信接口的票据ticket结束---------------------------------------------------


//--------------------------------------生成调用微信js-sdk所需要的参数开始---------------------------------------------------

    /**
     * 生成调用微信js-sdk的签名
     *
     * @param url
     * @return
     */
    public Map<String, String> sign(String url) {
        Map<String, String> ret = new HashMap<>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + WechatConstant.TICKET +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        return ret;
    }

//--------------------------------------生成调用微信js-sdk所需要的参数结束---------------------------------------------------


//-----------------------------------------------微信支付开始------------------------------------------------------------------

    /**
     * 获取微信支付请求包
     */
    public WechatPayPackage getWechatPayPackage(String openId, String body, String attach, String outTradeNo, String
            totalFee, String tradeType, String notifyUrl) {
        WechatPayApply wechatPayApply = new WechatPayApply();
        wechatPayApply.setOpenId(openId);
        wechatPayApply.setBody(body);
        wechatPayApply.setAttch(attach);
        wechatPayApply.setOutTradeNo(outTradeNo);
        wechatPayApply.setTotalFee(totalFee);
        wechatPayApply.setTradeType(tradeType);
        wechatPayApply.setNotifyUrl(notifyUrl);
        wechatPayApply.setAppId(appId);
        wechatPayApply.setMchId(mchId);
        wechatPayApply.setNonceStr(getNonceStr());
        wechatPayApply.setSpbillCreateIp(spbillCreateIp);
        wechatPayApply.setSign(getWeixinPaySign(wechatPayApply));

        String xml = "<xml>"
                + "<appid>" + wechatPayApply.getAppId() + "</appid>"
                + "<mch_id>" + wechatPayApply.getMchId() + "</mch_id>"
                + "<nonce_str>" + wechatPayApply.getNonceStr() + "</nonce_str>"
                + "<sign>" + wechatPayApply.getSign() + "</sign>"
                + "<body><![CDATA[" + wechatPayApply.getBody() + "]]></body>"
                + "<out_trade_no>" + wechatPayApply.getOutTradeNo() + "</out_trade_no>"
                + "<attach>" + wechatPayApply.getAttch() + "</attach>"
                + "<total_fee>" + wechatPayApply.getTotalFee() + "</total_fee>"
                + "<spbill_create_ip>" + wechatPayApply.getSpbillCreateIp() + "</spbill_create_ip>"
                + "<notify_url>" + wechatPayApply.getNotifyUrl() + "</notify_url>"
                + "<trade_type>" + wechatPayApply.getTradeType() + "</trade_type>"
                + "<openid>" + wechatPayApply.getOpenId() + "</openid>"
                + "</xml>";
        logger.info(xml);
        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        Map<String, String> map = doPostMap(createOrderURL, xml);
        logger.info("------------------------生成预支付信息成功--------------------------------");
        logger.info(map.toString());
        String nonceStr = "";
        String packages = "";
        String signType = "MD5";
        String timestamp = "";
        String paySign = "";
        if (map != null && map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")) {
            nonceStr = map.get("nonce_str");
            packages = "prepay_id=" + map.get("prepay_id");
            signType = "MD5";
            timestamp = create_timestamp();

            SortedMap<String, String> sortedMap = new TreeMap<>();
            sortedMap.put("appId", appId);
            sortedMap.put("timeStamp", timestamp);
            sortedMap.put("nonceStr", nonceStr);
            sortedMap.put("package", packages);
            sortedMap.put("signType", signType);

            String stringA = mapMontageAsString(sortedMap);
            String stringSignTemp = stringA + "&key=" + mchKey;
            paySign = MD5Digest(stringSignTemp).toUpperCase();
        }


        WechatPayPackage weixinPayPackage = new WechatPayPackage();
        weixinPayPackage.setNonceStr(nonceStr);
        weixinPayPackage.setPackages(packages);
        weixinPayPackage.setSignType(signType);
        weixinPayPackage.setTimestamp(timestamp);
        weixinPayPackage.setPaySign(paySign);

        return weixinPayPackage;
    }

    /**
     * 获取微信支付的签名
     *
     * @param weixinPayApply
     * @return
     */
    public String getWeixinPaySign(WechatPayApply weixinPayApply) {

        //将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序）
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("trade_type", weixinPayApply.getTradeType());
        packageParams.put("openid", weixinPayApply.getOpenId());
        packageParams.put("body", weixinPayApply.getBody());
        packageParams.put("attach", weixinPayApply.getAttch());
        packageParams.put("out_trade_no", weixinPayApply.getOutTradeNo());
        packageParams.put("total_fee", weixinPayApply.getTotalFee());
        packageParams.put("notify_url", weixinPayApply.getNotifyUrl());
        packageParams.put("spbill_create_ip", weixinPayApply.getSpbillCreateIp());
        packageParams.put("nonce_str", weixinPayApply.getNonceStr());
        packageParams.put("appid", weixinPayApply.getAppId());
        packageParams.put("mch_id", weixinPayApply.getMchId());

        //使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA
        String stringA = mapMontageAsString(packageParams);

        //在stringA最后拼接上key得到stringSignTemp字符串
        String stringSignTemp = stringA + "&key=" + mchKey;

        //对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写
        return MD5Digest(stringSignTemp).toUpperCase();
    }

//-----------------------------------------------微信支付结束------------------------------------------------------------------


//-----------------------------------------调用微信接口工具-----------------------------------------------------

    /**
     * 调用微信接口，获得返回值,支持GET方法
     */
    private Map<String, Object> getWechatReturn(Map<String, Object> params, String url) {
        try {
            String jsonStr = HttpUtils.httpGetRequest(url, params);
            logger.info("-----------------------jsonStr is " + jsonStr + "---------------------");
            if (!StringUtils.isEmpty(jsonStr)) {
                return JsonUtils.json2object(jsonStr, Map.class, String.class, Object.class);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        throw new ValidationException(MessageCodes.WECHAT_AUTHORIZE_FAILE);
    }

    /**
     * 调用微信接口，支持post方法，参数与返回数据都为xml格式的字符串
     */
    private static Map<String, String> doPostMap(String url, String params) {
        Map<String, String> result = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        try {
            if (params != null && !params.equals("")) {
                httpPost.setEntity(new StringEntity(params, "UTF-8"));
            }
            HttpResponse res = httpClient.execute(httpPost);
            String responseContent = EntityUtils.toString(res.getEntity(), "UTF-8");  //响应内容
            result = xmlStr2Map(responseContent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();  //关闭连接 ,释放资源
        }
        return result;
    }

    //-----------------------------------------调用微信接口工具-----------------------------------------------------


//----------------------------------------相关工具类开始--------------------------------------------------------------------

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 已经经过字典序排序的map拼接为一个字符串key1=value1&key2=value2&key3=value3
     */
    public static String mapMontageAsString(SortedMap<String, String> sortedMap) {
        StringBuffer sb = new StringBuffer();
        Set es = sortedMap.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (v != null && !v.equals("")) {
                sb.append(k + "=" + v + "&");
            }
        }
        return sb.substring(0, sb.lastIndexOf("&"));
    }

    /**
     * MD5加密
     */
    public static String MD5Digest(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 将拼接而成的字符串进行MD5加密
            byte[] digest = md.digest(content.getBytes("utf-8"));
            //将加密后的字节数组转成十六进制的字符串
            return byteArrayToHexStr(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 字节数组转换为十六进制的字符串
     *
     * @param byteArray
     * @return
     */
    public static String byteArrayToHexStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 字节转换为十六进制的字符串
     *
     * @param mByte
     * @return
     */
    public static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     */
    public static Map<String, String> xmlStr2Map(String strxml) throws Exception {
        if (null == strxml || "".equals(strxml)) {
            return null;
        }

        Map<String, String> m = new HashMap<String, String>();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }

    /**
     * 获取子结点的xml
     *
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    /**
     * 生成随机字符串
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取随机字符串
     *
     * @return
     */
    public static String getNonceStr() {
        // 随机数
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currTime = outFormat.format(now);

        // 8位日期
        String strTime = currTime.substring(8, currTime.length());

        // 四位随机数
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < 4; i++) {
            num = num * 10;
        }
        String strRandom = (int) ((random * num)) + "";

        // 10位序列号,可以自行调整。
        return strTime + strRandom;
    }

    /**
     * 生成时间戳
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
