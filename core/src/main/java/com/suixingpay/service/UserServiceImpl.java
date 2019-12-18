package com.suixingpay.service;

import com.suixingpay.mapper.ButlerUserMapper;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.util.TokenUtil;
import com.suixingpay.vo.ButlerUserVO;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户服务的实现
 * <p>
 * Created by Jalr4ever on 2019/12/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ButlerUserMapper butlerUserMapper;

    @Override
    public ButlerUserVO userLogIn(ButlerUser butlerUser) {
        // 查询出用户的集合
        List<ButlerUser> butlerUserList = butlerUserMapper.selectUserAnyCondition(butlerUser);

        // 用户集合为空，说明查不到
        if (butlerUserList.size() == 0) {
            return new ButlerUserVO();
        } else {
            // 查询到的用户
            ButlerUser user = butlerUserList.get(0);
            // TODO: 2019/12/18 取到 token 做相关响应逻辑
        }
        return null;
    }

}
