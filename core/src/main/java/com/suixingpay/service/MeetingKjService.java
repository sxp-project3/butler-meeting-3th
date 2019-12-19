package com.suixingpay.service;

import com.suixingpay.pojo.Meeting;

import java.util.List;
import java.util.Map;

/**
 * @Author: kongjian
 * @Date: 2019/12/18
 */

public interface MeetingKjService {
    List<Meeting> getValidMeeting(Integer userId);

    Meeting getOne(Integer meetingId);

    List<Meeting> getMyMeeting(Integer userId);

    List<Meeting> searchMeeting(Map params);
}
