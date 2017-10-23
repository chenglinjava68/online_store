package com.yuan.service;

/**
 * @author joryun ON 2017/10/22.
 */
public class RedisKeyPrefix {

    public static String buildUserIdToCart(String userId) {
        return "user.uid.cart:" + userId;
    }

    public static String buildUserIdToDistrict(String userId){
        return "user.uid.district:" + userId;
    }

    public static String pictureCaptcha(String uuid){
        return "manager.captcha:"+uuid;
    }

    public static String buildTokenToUserId(String token) {
        return "user.token.uid:" + token;
    }

    public static String buildUserIdToToken(Integer userId) {
        return "user.uid.token:" + userId;
    }

    public static String buildTokenToManagerId(String token) {
        return "manager.token.uid:" + token;
    }

    public static String buildManagerIdToToken(Integer managerId) {
        return "manager.uid.token:" + managerId;
    }

    public static String mobilePhoneCaptchaUser(String mobilePhone) {
        return "user.mobilePhone.captcha:" + mobilePhone;
    }
}
