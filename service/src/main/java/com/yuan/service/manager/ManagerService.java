package com.yuan.service.manager;

import com.yuan.common.exception.MessageCodes;
import com.yuan.common.exception.ValidationException;
import com.yuan.common.utils.BeanUtils;
import com.yuan.common.utils.Md5Encrypt;
import com.yuan.common.utils.StringUtils;
import com.yuan.models.FlagType;
import com.yuan.models.manager.Manager;
import com.yuan.models.manager.ManagerDao;
import com.yuan.service.RedisKeyPrefix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author joryun ON 2017/10/22.
 */
@Service
public class ManagerService {

    private Logger logger = LoggerFactory.getLogger(ManagerService.class);

    @Resource
    private ManagerDao managerDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> operations;

    /**
     * 管理员登录
     *
     * @param account
     * @param password
     * @param captcha
     * @param rightCaptchaUUid
     * @return
     */
    public ManagerLoginSuccessVO login(String account, String password, String captcha, String rightCaptchaUUid) {
        //从redis中获得该验证码
        String rightCaptcha = operations.get(RedisKeyPrefix.pictureCaptcha(rightCaptchaUUid));
        if (StringUtils.isEmpty(rightCaptcha)) {
            //验证码已失效
            throw new ValidationException(MessageCodes.AUTH_PICCAPTCHA_LOST);
        } else {
            //判断验证码是否正确
            if (!rightCaptcha.equals(captcha)) {
                throw new ValidationException(MessageCodes.AUTH_PICCAPTCHA_WRONG);
            } else {
                //判断账号密码是否存在
                String md5Password = Md5Encrypt.md5(account + password);
                Manager manager = managerDao.findByAccountAndPasswordAndFlag(account, md5Password, FlagType.FALSE);
                logger.info("login:{} {}", account, md5Password);
                if (manager != null) {
                    Integer managerId = manager.getManagerId();
                    String token = generateToken();
                    logger.info("token:{} {}", managerId, token);
                    //清除旧的缓存
                    deleteManagerRedisCache(managerId);
                    operations.set(RedisKeyPrefix.buildTokenToManagerId(token), managerId + "", 6, TimeUnit.DAYS);
                    operations.set(RedisKeyPrefix.buildManagerIdToToken(managerId), token, 6, TimeUnit.DAYS);

                    ManagerLoginSuccessVO managerLoginSuccessVO = new ManagerLoginSuccessVO(
                            manager.getManagerName(),
                            manager.getManagerRole(),
                            manager.getManagerId(), token);
                    //删除验证码缓存
                    deleteCaptchaRedisCache(rightCaptchaUUid);
                    return managerLoginSuccessVO;
                } else {
                    throw new ValidationException(MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG);
                }
            }
        }
    }

    /**
     * 清空redis缓存中的验证码
     *
     * @param uuid
     */
    private void deleteCaptchaRedisCache(String uuid) {
        final String captcha = operations.get(RedisKeyPrefix.pictureCaptcha(uuid));
        if (!StringUtils.isEmpty(captcha)) {
            redisTemplate.delete(Arrays.asList(RedisKeyPrefix.pictureCaptcha(uuid)));
        }
    }

    /**
     * 清空redis缓存中的管理员数据
     *
     * @param managerId
     */
    private void deleteManagerRedisCache(Integer managerId) {
        final String token = operations.get(RedisKeyPrefix.buildManagerIdToToken(managerId));
        if (!StringUtils.isEmpty(token)) {
            redisTemplate.delete(Arrays.asList(RedisKeyPrefix.buildTokenToManagerId(token), RedisKeyPrefix
                    .buildManagerIdToToken(managerId)));
        }
    }

    /**
     * 生成token
     *
     * @return
     */
    private String generateToken() {
        final String uuid = UUID.randomUUID().toString();
        return org.springframework.util.StringUtils.deleteAny(uuid, "-") + Long.toString(System.currentTimeMillis(),
                Character.MAX_RADIX);
    }

    /**
     * 添加管理员
     *
     * @param managerAddVO
     * @return
     */
    public ManagerVO addManager(ManagerAddVO managerAddVO) {
        //TODO 店铺管理员需设置店铺

        Manager manager = new Manager();
        BeanUtils.copyNonNullProperties(managerAddVO, manager);
        manager.setPassword(Md5Encrypt.md5(managerAddVO.getAccount() + managerAddVO.getPassword()));
        manager.setCreateTime(new Date());
        manager.setFlag(FlagType.FALSE);
        managerDao.save(manager);
        return toManagerVO(manager);
    }

    /**
     * get -> Manager
     *
     * @param managerId
     * @return
     */
    public Manager getManager(Integer managerId) {
        Manager manager = managerDao.findByManagerIdAndFlag(managerId, FlagType.FALSE);
        Assert.notNull(manager, MessageCodes.MANAGER_IS_NOT_EXIST);
        return manager;
    }

    /**
     * manager -> managerVO
     *
     * @param manager
     * @return
     */
    private ManagerVO toManagerVO(Manager manager) {
        //TODO 考虑返回字段新增店铺id与店铺名字，当店铺id不为空时，才存在店铺名字

        ManagerVO managerVO = new ManagerVO();
        BeanUtils.copyNonNullProperties(manager, managerVO);
        return managerVO;
    }

}
