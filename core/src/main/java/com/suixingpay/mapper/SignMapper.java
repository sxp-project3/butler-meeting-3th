package com.suixingpay.mapper;

import com.suixingpay.pojo.Sign;

import java.util.List;


/**
 * @author 段思宇，黄宇萧
 * @program: butler-meeting-3th
 * @description: 报名、签到服务
 * @date 2019-12-18
 */
public interface SignMapper {

    /**
     * 报名功能，插入报名信息
     * @param sign 签到、报名信息实体
     * @return
     */
    int signUpActive(Sign sign);

    /**
     *根据报名信息查询出当前会议下的所有用户
     * @param sign 签到、报名信息实体
     * @return 用户实体
     */
    List<Sign> selectIdByMeeting(Sign sign);

    /**
     *在已经报名的情况下，更改签到状态实现签到
     * @param sign 签到、报名信息实体
     * @return
     */
    int updateSignIn(Sign sign);

    /**
     *在未报名的情况下，插入一条数据记录签到信息
     * @param sign 签到、报名信息实体
     * @return
     */
    int insertSignIn(Sign sign);

    /**
     *查询出当前会议下所有以签到的用户总数
     * @param integer 签到、报名信息实体
     * @return
     */
    int selectCountSignIn(Integer integer);

    /**
     *查询出当前会议下所有以报名的用户总数
     * @param integer 签到、报名信息实体
     * @return
     */
    int selectCountSignUp(Integer integer);

    /**
     *查询出当前会议下某个用户的所有报名、签到信息
     * @param sign 签到、报名信息实体
     * @return
     */
    Sign selectWithOutIdAndUserId(Sign sign);

}