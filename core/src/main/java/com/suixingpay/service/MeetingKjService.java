package com.suixingpay.service;

import com.suixingpay.pojo.Meeting;
import com.suixingpay.query.SearchMeetingParamQuery;
import com.suixingpay.takin.data.domain.PageImpl;
import com.suixingpay.takin.mybatis.domain.Pagination;

import java.util.List;

/**
 * @Author: kongjian
 * @Date: 2019/12/18
 */

public interface MeetingKjService {
    // List<Meeting> getValidMeeting(Integer userId, Pagination pagination);
    PageImpl getValidMeeting(Integer userId, Pagination pagination);

    Meeting getOne(Integer meetingId);

    List<Meeting> getMyMeetings(Integer userId);

    List<Meeting> searchMeeting(SearchMeetingParamQuery searchMeetingParamQuery);
}
