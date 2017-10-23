package com.yuan.service.user;

import com.yuan.common.constants.ModelContants;
import com.yuan.common.exception.MessageCodes;
import com.yuan.common.exception.ValidationException;
import com.yuan.common.utils.BeanUtils;
import com.yuan.common.utils.StringUtils;
import com.yuan.models.FlagType;
import com.yuan.models.user.User;
import com.yuan.models.user.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author joryun ON 2017/10/22.
 */
@Service
public class UserWebService {

    private static final Logger logger = LoggerFactory.getLogger(UserWebService.class);

    @Resource
    private UserDao userDao;

    /**
     * 去到个人中心
     *
     * @param userId
     * @return
     */
    public UserVO toUserCenter(Integer userId) {
        User user = userDao.findOne(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyNonNullProperties(user, userVO);
        return userVO;
    }

    /**
     * 检查是否已绑定手机
     *
     * @param userId
     * @return
     */
    public UserWebCheckVO checkHasBind(Integer userId) {
        User user = userDao.findOne(userId);
        if (StringUtils.isEmpty(user.getPhone())) {
            return new UserWebCheckVO(ModelContants.UserContant.BIND_FALSE);
        } else {
            return new UserWebCheckVO(ModelContants.UserContant.BIND_TRUE, user.getPhone());
        }
    }

    /**
     * 通过手机号校验手机号码是否已被注册
     *
     * @param userId
     * @param mobilePhone
     */
    public void checkMobilePhone(Integer userId, String mobilePhone) {
        User user = userDao.findByPhoneAndFlag(mobilePhone, FlagType.FALSE);
        if (user != null) {
            throw new ValidationException(MessageCodes.MOBILE_PHONE_HAS_BIND);
        }
    }

    /**
     * 绑定手机号
     *
     * @param userId
     * @param mobilePhone
     */
    @Transactional
    public void bindMobilePhone(Integer userId, String mobilePhone) {
        User user = userDao.findOne(userId);
        user.setPhone(mobilePhone);
        userDao.save(user);
    }

}
