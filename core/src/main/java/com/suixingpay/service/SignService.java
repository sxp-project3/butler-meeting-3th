package com.suixingpay.service;

import com.suixingpay.pojo.Sign;
import com.suixingpay.response.Response;

import java.util.List;

public interface SignService {
    /**
     *报名
     * @param sign
     * @return
     */
    int signUpActive(Sign sign);

    /**
     * 签到的查询服务，用作判断是否已报名
     * @param sign
     * @return
     */
    List<Integer> selectIdByMeeting(Sign sign);

    /**
     * 已报名的签到服务
     * @param sign
     * @return
     */
    int updateSignIn(Sign sign);

    /**
     * 未报名的签到服务
     * @param sign
     * @return
     */
    int insertSignIn(Sign sign);

    int selectCountSignIn(Integer integer);

    int selectCountSignUp(Integer integer);

    Sign selectWithOutIdAndUserId(Sign sign);
}
