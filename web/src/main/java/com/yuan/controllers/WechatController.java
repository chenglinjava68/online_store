package com.yuan.controllers;

import com.yuan.common.constants.WechatConstant;
import com.yuan.common.exception.MessageCodes;
import com.yuan.common.exception.ValidationException;
import com.yuan.common.utils.StringUtils;
import com.yuan.common.utils.WechatUtils;
import com.yuan.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author joryun ON 2017/10/22.
 */
@Controller
@Api(tags = "微信接口模块")
@RequestMapping(value = "/api/customer/wechat")
public class WechatController {

    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Value("${wechat.redirectUrl}")
    private String redirectUrl;

    @Value("${wechat.appId}")
    private String appId;

    @Resource
    private UserService userService;

    @Resource
    private WechatUtils wechatUtils;

    @GetMapping("authorize")
    @ApiOperation("微信授权回调接口,成功返回跳转到一个页面，并携带用户标示token，失败抛出异常")
    public String authorize(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "state", required = false) String state) {
        logger.info("-------------------------code：{}------------------------------", code);
        logger.info("-------------------------state: {}----------------------------", state);
        if (StringUtils.isEmpty(code)) {
            throw new ValidationException(MessageCodes.WECHAT_AUTHORIZE_FAILE);
        } else {
            Map<String, Object> authorizeMap = wechatUtils.authorize(code);
            logger.info("-------------------------authorizeMap: {}----------------------------", authorizeMap
                    .toString());
            Map<String, Object> userInfoMap = wechatUtils.getUserInfo((String) authorizeMap.get("access_token"),
                    (String) authorizeMap
                    .get("openid"));
            logger.info("-------------------------userInfo: {}----------------------------", userInfoMap.toString());
            String token = userService.login(userInfoMap);
            logger.info("------------------------user token is ：{}", token);
            logger.info("------------------------redirect url is：{}", state + "?token=" + token);
            return "redirect:" + state + "?token=" + token;
        }
    }

    @GetMapping("getSign")
    @ApiOperation("获得js-sdk授权凭据")
    @ResponseBody
    public Map<String, String> getSign(
            @RequestParam(value = "url", required = false, defaultValue = "-1") String url) {
        wechatUtils.getToken();
        logger.info("------------------------token:{}----------------", WechatConstant.TOKEN);
        logger.info("------------------------ticket:{}---------", WechatConstant.TICKET);
        logger.info("------------------------url:{}----------------", url);
        Map<String, String> map = wechatUtils.sign(url);
        map.put("appId", appId);
        return map;
    }

}
