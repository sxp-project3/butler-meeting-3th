package com.suixingpay.mapper;

import com.suixingpay.pojo.Sign;

import java.util.List;


public interface SignMapper {

    int signUpActive(Sign sign);

    List<Sign> selectIdByMeeting(Sign sign);

    int updateSignIn(Sign sign);

    int insertSignIn(Sign sign);

}