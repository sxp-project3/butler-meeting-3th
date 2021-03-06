package com.suixingpay.service;

import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.vo.ButlerUserVO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户相关的服务
 * <p>
 * Created by jalr on 2019/12/18.
 */
@Validated
public interface UserService {
    /**
     * 验证用户登录，如果登录，则返回该用户的实体信息(显示层实体)
     *
     * @param butlerUser 用户实体
     * @return 返回该用户的所有信息，包括 token
     */
    ButlerUserVO userLogIn(ButlerUser butlerUser);

    /**
     * 根据用户 token 来注销当前 token 的用户
     *
     * @param token 用户 token
     * @return 返回注销登录的用户全部
     */
    ButlerUserVO userLogOut(String token);

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

    /**
     * 根据推荐码查出用户 id
     *
     * @param referralCode 推荐码
     * @return 如果为空，则返回 null ，不为空则返回对应的用户 id
     */
    Integer getUserIdByReferCode(@NotBlank(message = "推荐码不可为空！") String referralCode);
}
