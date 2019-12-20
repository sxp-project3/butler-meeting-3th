package com.suixingpay.service;

import com.suixingpay.pojo.Sign;

import java.util.List;


/**
 * @author 段思宇，黄宇萧
 * @program: butler-meeting-3th
 * @description: 报名、签到服务
 * @date 2019-12-18
 */
public interface SignService {
    /**
     *报名
     * @param sign 签到、报名信息实体
     * @return
     */
    int signUpActive(Sign sign);

    /**
     * 签到的查询服务，用作判断是否已报名
     * @param sign 签到、报名信息实体
     * @return 当前会议下的所有用户id
     */
    List<Integer> selectIdByMeeting(Sign sign);

    /**
     * 已报名的签到服务
     * @param sign 签到、报名信息实体
     * @return
     */
    int updateSignIn(Sign sign);

    /**
     * 未报名的签到服务
     * @param sign 签到、报名信息实体
     * @return
     */
    int insertSignIn(Sign sign);

    /**
     * 未报名的签到服务
     * @param integer 签到状态
     * @return
     */
    int selectCountSignIn(Integer integer);

    /**
     * 未报名的签到服务
     * @param integer 报名状态
     * @return
     */
    int selectCountSignUp(Integer integer);

    /**
     * 未报名的签到服务
     * @param sign 签到、报名信息实体
     * @return 包含当前会议下的某一用户的所有信息
     */
    Sign selectWithOutIdAndUserId(Sign sign);
}
