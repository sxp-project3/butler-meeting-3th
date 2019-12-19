package com.suixingpay.service;

import com.auth0.jwt.interfaces.Claim;
import com.suixingpay.mapper.ButlerUserMapper;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.util.TokenUtil;
import com.suixingpay.vo.ButlerUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ButlerUserMapper butlerUserMapper;

    @Override
    public ButlerUserVO userLogIn(ButlerUser butlerUser) {

        // 查询出用户的集合
        List<ButlerUser> butlerUserList = butlerUserMapper.selectUserAnyCondition(butlerUser);

        // 返回的显示层对象，默认是没登录，为空的
        ButlerUserVO butlerUserVO = new ButlerUserVO();

        // 用户集合为空，说明查不到，不为空，则查询到
        if (butlerUserList.size() != 0) {
            // 查询到的用户
            ButlerUser user = butlerUserList.get(0);
            String token = TokenUtil.creatToken(user);
            // 测试解密 token
            parseUser(token);
            butlerUserVO = new ButlerUserVO(user, token);
        }

        return butlerUserVO;
    }

    @Override
    public ButlerUserVO parseUser(String token) {
        Map<String, Claim> map = TokenUtil.verifyToken(token);
        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            Claim val = (Claim) entry.getValue();
            log.warn(key + "---" + val.asString());
        }
        return null;
    }

}
