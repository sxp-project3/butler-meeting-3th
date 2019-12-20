package com.suixingpay.service;

import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.vo.ButlerUserVO;

import java.util.Date;
import java.util.List;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户相关的服务
 * <p>
 * Created by jalr on 2019/12/18.
 */
public interface UserService {
    /**
     * 验证用户登录，如果登录，则返回该用户的实体信息(显示层实体)
     *
     * @param butlerUser 用户实体
     * @return 返回该用户的所有信息，包括 token
     */
    ButlerUserVO userLogIn(ButlerUser butlerUser);

    /**
     * 根据 token，返回一个用户实体，token 的有效性，通过 redis 缓存判断
     *
     * @param token 用户的 token
     * @return 返回用户的所有信息，包括 token
     */
    ButlerUserVO parseUser(String token);

    /**
     * 根据 token，判断用户是否已登录，如果有登录，则在 redis 缓存中查询，返回登录的有效期
     *
     * @param token 传入的用户 token
     * @return 如果 token 无效，表示未登录，如果 token 有就表示有登录，并返回有效的截至时间
     */
    String isUserLogin(String token);
}
