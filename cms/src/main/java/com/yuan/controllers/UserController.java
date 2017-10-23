package com.yuan.controllers;

import com.yuan.service.user.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author joryun ON 2017/10/22.
 */
@RestController
@RequestMapping(value = "/api/admin/user")
@Api(tags = "平台用户模块")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

}
