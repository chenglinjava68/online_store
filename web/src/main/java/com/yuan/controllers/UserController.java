package com.yuan.controllers;

import com.yuan.service.user.UserWebCheckVO;
import com.yuan.service.user.UserWebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author joryun ON 2017/10/22.
 */
@RestController
@Api(tags = "用户模块")
@RequestMapping("/api/customer/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserWebService userWebService;

//    @Autowired
//    private AliSMSUtils aliSMSUtils;

    @GetMapping("checkHasBind")
    @ApiOperation("检查是否绑定手机")
    public UserWebCheckVO checkHasBind() {
        return userWebService.checkHasBind(UserHelper.getUserId());
    }

//    @GetMapping("getVerCode")
//    @ApiOperation("用户绑定手机时获得验证码")
//    public void getVerCode(@RequestParam String mobilePhone) {
//        userWebService.checkMobilePhone(UserHelper.getUserId(), mobilePhone);
//        logger.info("-------------------------phone：{}------------------------------", mobilePhone);
//        try {
//            aliSMSUtils.sendSMSCaptcha(mobilePhone);
//        } catch (ClientException e) {
//            logger.info("-------------验证码异常发送失败-----------------", e);
//            throw new ValidationException(MessageCodes.AUTH_PHOCAPTCHA_SEND_FAIL);
//        }
//    }

//    @PutMapping("bindMobilePhone")
//    @ApiOperation("绑定手机号码")
//    public void bindMobilePhone(@RequestBody @Valid UserWebBindPhoneVO userWebBindPhoneVO) {
//        if (aliSMSUtils.checkSMSCaptcha(userWebBindPhoneVO.getMobilePhone(), userWebBindPhoneVO.getVerCode())) {
//            logger.info("-------------------------通过验证码验证--------------------");
//            userWebService.bindMobilePhone(UserHelper.getUserId(), userWebBindPhoneVO.getMobilePhone());
//            //检查用户身份
//            userWebService.checkUserRole(UserHelper.getUserId(), userWebBindPhoneVO.getMobilePhone());
//        } else {
//            logger.info("-------------------------验证码验证错误--------------------");
//            throw new ValidationException(MessageCodes.AUTH_PHOCAPTCHA_WRONG);
//        }
//    }

}
