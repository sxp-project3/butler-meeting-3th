package com.suixingpay.service;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.mapper.MeetingMapper;
import com.suixingpay.pojo.Meeting;
import com.suixingpay.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/18 15:37
 */
@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    @Override
    public void addMeeting(Meeting Meeting) {
        try {
            Integer result = meetingMapper.insertMeeting(Meeting);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
