package com.suixingpay.service;

import com.suixingpay.mapper.MeetingMapper;
import com.suixingpay.pojo.Meeting;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public List<Meeting> getValidMeeting(Integer userId) {
        Date now = new Date();
        // 这里还没有完成，应该获取到此用户的所有上级用户id
        List<Integer> userIds = new ArrayList<>();
        userIds.add(0);
        userIds.add(userId);

        List<Meeting> meetings = meetingMapper.getListForFrontShow(now, userIds);
        return meetings;
    }

    @Override
    public Meeting getOne(Integer id) {
        return meetingMapper.selectOneById(id);
    }

    @Override
    public List<Meeting> getMyMeeting(Integer userId) {

    }

//    @Override
//    public List<Meeting> get
}
