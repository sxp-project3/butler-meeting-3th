package com.suixingpay.service;

import com.github.pagehelper.PageHelper;
import com.suixingpay.mapper.MeetingMapper;
import com.suixingpay.pojo.ButlerSubordinates;
import com.suixingpay.pojo.Meeting;
// import com.suixingpay.vo.SearchMeetingParamVo;
import com.suixingpay.query.SearchMeetingParamQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: kongjian
 * @Date: 2019/12/18
 */

@Service
@Slf4j
public class MeetingKjServiceImpl implements MeetingKjService {
    @Resource
    private MeetingMapper meetingMapper;

    @Autowired
    private ButlerSubordinatesServcie butlerSubordinatesServcie;

    @Override
    public List<Meeting> getValidMeeting(Integer userId) {
        Date now = new Date();
        // 这里还没有完成，应该获取到此用户的所有上级用户id

        List<Integer> userIds = butlerSubordinatesServcie.selectUserIdBySubId(userId);
        userIds.add(0);
        // userIds.add(userId);
        List<Meeting> meetings = meetingMapper.getListForFrontShow(now, userIds);
//        List<Integer> meetingIds = new ArrayList<>();
//        for (Meeting oneMeeting:
//                meetings) {
//
//            meetingIds.add(oneMeeting.getId());
//        }
//
//        signService.selectWithOutIdAndUserId(Sign sign)


        return meetings;
    }

    @Override
    public Meeting getOne(Integer id) {
        return meetingMapper.selectOneById(id);
    }

    @Override
    public List<Meeting> getMyMeetings(Integer userId) {
        List<Meeting> meetings = meetingMapper.getMyCreateList(userId);
        return meetings;
    }

    @Override
    public List<Meeting> searchMeeting(SearchMeetingParamQuery searchMeetingParamQuery) {
        Date now = new Date();
        searchMeetingParamQuery.setNowDate(now);
        PageHelper.startPage(searchMeetingParamQuery.getPageNum(), searchMeetingParamQuery.getPageSize());
        List<Meeting> meetings = meetingMapper.paramSearchList2(searchMeetingParamQuery);
        return meetings;
    }
}
