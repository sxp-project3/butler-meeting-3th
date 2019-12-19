package com.suixingpay.service;

import com.suixingpay.pojo.Sign;
import com.suixingpay.response.Response;

import java.util.List;

/**
 * @author hyx
 */
public interface SignService {

    Response insertByIn(Sign sign);

    int insertByUp(Sign sign);

    int updateByIn(Sign sign);

    List<Integer> selectByMeetId(Integer integer);

    List<Sign> selectAll();
}
