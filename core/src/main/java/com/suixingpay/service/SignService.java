package com.suixingpay.service;

import com.suixingpay.pojo.Sign;
import com.suixingpay.response.Response;

import java.util.List;

public interface SignService {
    int signUpActive(Sign sign);

    List<Integer> selectIdByMeeting(Sign sign);

    int updateSignIn(Sign sign);

    int insertSignIn(Sign sign);
}
