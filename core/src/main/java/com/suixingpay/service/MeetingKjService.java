package com.suixingpay.service;

import com.suixingpay.pojo.Meeting;
import com.suixingpay.query.SearchMeetingParamQuery;

import java.util.List;

/**
 * @Author: kongjian
 * @Date: 2019/12/18
 */

public interface MeetingKjService {
    List<Meeting> getValidMeeting(Integer userId);

    Meeting getOne(Integer meetingId);

    List<Meeting> getMyMeetings(Integer userId);

    List<Meeting> searchMeeting(SearchMeetingParamQuery searchMeetingParamQuery);
}
