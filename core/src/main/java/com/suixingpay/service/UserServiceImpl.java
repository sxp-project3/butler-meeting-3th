package com.suixingpay.service;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.suixingpay.mapper.ButlerUserMapper;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.util.JacksonUtil;
import com.suixingpay.util.TokenUtil;
import com.suixingpay.vo.ButlerUserVO;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户服务的实现
 * <p>
 * Created by Jalr4ever on 2019/12/18.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    /**
     * 用户的实体 Mapper
     */
    @Resource
    private ButlerUserMapper butlerUserMapper;


    /**
     * Redis 命令模板
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存 key，用户 id
     */
    private static final String USER_ID = "user:id:";


    /**
     * 缓存 val，用户 token 的有效期
     */
    private static final String TOKEN_VALIDITY_TIME = "token:validity:time:";

    /**
     * 缓存用户状态，也就是缓存 HASH 在 Redis 的名字
     */
    private static final String USER_STATUS = "User_Status_Cache";


    /**
     * 缓存 token 过期时间，单位 h
     */
    private static final int EXPIRE_TIME = 24;

    @Override
    public ButlerUserVO userLogIn(ButlerUser butlerUser) {

        // 查询出用户的集合
        List<ButlerUser> butlerUserList = butlerUserMapper.selectUserAnyCondition(butlerUser);

        // 返回的显示层对象，默认是没登录，为空的
        ButlerUserVO butlerUserVO = new ButlerUserVO();

        // 用户集合为空，说明查不到，不为空，则查询到
        if (butlerUserList.size() != 0) {

            // 查询到的用户，返回用户信息给前端
            ButlerUser user = butlerUserList.get(0);
            String token = TokenUtil.creatToken(user);

            // 缓存 token 到 redis， 并设置过期时间
            String cacheKey = USER_ID + user.getId();
            String cacheVal = TOKEN_VALIDITY_TIME + System.currentTimeMillis();
            redisTemplate.opsForValue().set(cacheKey, cacheVal);
            redisTemplate.expire(cacheKey, EXPIRE_TIME, TimeUnit.HOURS);

            // 生成用户视图对象
            butlerUserVO = new ButlerUserVO(user, token);

        }

        return butlerUserVO;
    }

    @Override
    public ButlerUserVO userLogOut(String token) {
        return null;
    }

    @Override
    public ButlerUserVO parseUser(String token) {

        if (token == null) {
            return new ButlerUserVO();
        }

        // 通过 token 解析用户信息
        return TokenUtil.verifyToken(token);

    }

    @Override
    public String isUserLogin(String token) {

        // 解析 token
        ButlerUserVO butlerUserVO = parseUser(token);

        // 在缓存中查询是否有该 token，不存在返回空
        String time = (String) redisTemplate.opsForValue().get(USER_ID + butlerUserVO.getId());

        // 取到时间戳并返回
        if (time == null) {
            return null;
        } else {
            String[] str = time.split(":");
            String result = str[str.length - 1];
            return JacksonUtil.dateToString(Double.parseDouble(result));
        }
    }

    @Override
    public Integer getUserIdByReferCode(String referralCode) {
        ButlerUser butlerUser = new ButlerUser();
        butlerUser.setAccount(referralCode);
        List<ButlerUser> referCodeList = butlerUserMapper.selectUserAnyCondition(butlerUser);
        if (referCodeList.size() == 0) {
            return null;
        } else {
            return referCodeList.get(0).getId();
        }
    }
}
