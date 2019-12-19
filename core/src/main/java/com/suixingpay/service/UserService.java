package com.suixingpay.service;

import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.vo.ButlerUserVO;

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
}
