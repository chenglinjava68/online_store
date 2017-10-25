package com.yuan.controllers;

import com.yuan.common.constants.ModelContants;
import com.yuan.service.manager.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author joryun ON 2017/10/22.
 */
@RestController
@Api(tags = "管理员模块")
@RequestMapping(value = "/api/admin/manager")
public class ManagerController {

    private Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Resource
    private ManagerService managerService;

    @PostMapping("login")
    @ApiOperation(value = "管理员登录")
    public ManagerLoginSuccessVO login(
            @RequestBody @Valid ManagerLoginVO managerLoginVO, HttpServletRequest request) {
        return managerService.login(
                managerLoginVO.getAccount(), managerLoginVO.getPassword(), managerLoginVO.getCaptcha(),
                managerLoginVO.getUuid());
    }

    @PostMapping
    @ApiOperation("添加管理员")
    @RequiresRoles(logical = Logical.OR, value = {ModelContants.ManagerContant.ADMIN_MANAGER})
    public ManagerVO add(@RequestBody @Valid ManagerAddVO managerAddVO) {
        return managerService.addManager(managerAddVO);
    }

}
