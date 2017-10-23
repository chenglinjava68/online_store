package com.yuan.service.user;

import com.yuan.common.exception.MessageCodes;
import com.yuan.common.utils.StringUtils;
import com.yuan.models.FlagType;
import com.yuan.models.user.User;
import com.yuan.models.user.UserDao;
import com.yuan.models.user.UserType;
import com.yuan.service.RedisKeyPrefix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author joryun ON 2017/10/22.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> operations;

    @Resource
    private UserDao userDao;

    /**
     * 用户登陆
     *
     * @param userInfoMap
     * @return
     */
    public String login(Map<String, Object> userInfoMap) {
        User user = saveUser(userInfoMap);
        final Integer userId = user.getUserId();
        deleteRedisCache(userId);
        final String token = addRedisCache(userId);
        return token;
    }

    /**
     * 更新用户或者保存用户
     *
     * @param userInfoMap
     * @return
     */
    @Transactional
    public User saveUser(Map<String, Object> userInfoMap) {
        String openId = (String) userInfoMap.get("openid");
        String headImgUrl = (String) userInfoMap.get("headimgurl");
        String nickName = (String) userInfoMap.get("nickname");
        Integer sex = (Integer) userInfoMap.get("sex");
        User user = isExit(openId);
        if (user != null) {
            user.setHeadImage(headImgUrl);
            user.setNickname(nickName);
            user.setOpenId(openId);
            user.setUpdateTime(new Date());
            userDao.save(user);

        } else {
            user = new User();
            user.setSex(sex);
            user.setOpenId(openId);
            user.setNickname(nickName);
            user.setHeadImage(headImgUrl);
            user.setFlag(FlagType.FALSE);
            user.setRole(UserType.Ordinary);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userDao.save(user);
        }
        return user;
    }

    /**
     * 判断用户是否存在
     *
     * @param openId
     * @return
     */
    public User isExit(String openId) {
        User user = userDao.findByOpenIdAndFlag(openId, FlagType.FALSE);
        if (user != null) {
            logger.info("用户存在，用户openId为：" + openId + "  用户名为：" + user.getNickname());
            return user;
        } else {
            return null;
        }
    }


    private String addRedisCache(Integer userId) {
        String token = generateToken();
        final String userIdStr = String.valueOf(userId);
        operations.set(RedisKeyPrefix.buildTokenToUserId(token), userIdStr, 6, TimeUnit.DAYS);
        operations.set(RedisKeyPrefix.buildUserIdToToken(userId), token, 6, TimeUnit.DAYS);
        return token;
    }

    private String generateToken() {
        final String uuid = UUID.randomUUID().toString();
        return org.springframework.util.StringUtils.deleteAny(uuid, "-") + Long.toString(System.currentTimeMillis(),
                Character.MAX_RADIX);
    }

    private void deleteRedisCache(Integer userId) {
        final String token = operations.get(RedisKeyPrefix.buildUserIdToToken(userId));
        if (!StringUtils.isEmpty(token)) {
            redisTemplate.delete(Arrays.asList(RedisKeyPrefix.buildTokenToUserId(token), RedisKeyPrefix
                    .buildUserIdToToken(userId)));
        }
    }

    /**
     * 根据openId获得用户
     *
     * @param openId
     * @return
     */
    public User getUserByOpenId(String openId) {
        User user = userDao.findByOpenIdAndFlag(openId, FlagType.FALSE);
        logger.info("----------------------------user nick name {}", user.getNickname());
        Assert.notNull(user, MessageCodes.USER_IS_NOT_EXIT);
        return user;
    }

    /**
     * get -> User
     *
     * @param userId
     * @return
     */
    public User getUser(Integer userId) {
        User user = userDao.findOne(userId);
        Assert.notNull(user, MessageCodes.USER_IS_NOT_EXIT);
        return user;
    }

}