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


    //插入一条会议，对应新建会议功能
    @Override
    public Integer addMeeting(Meeting Meeting) {
        Integer result = meetingMapper.insertMeeting(Meeting);
        if (result >= 1) {
            return 1;
        }
        return 0;


    }
    /**
     * 根据会议id修改会议信息
     *
     * @param Meeting 会议实体
     * @return
     */
    @Override
    public Integer updateMeetingById(Meeting Meeting) {
        Integer result = meetingMapper.updateMeetingById(Meeting);
        if (result >= 1) {
            return 1;
        }
        return 0;


    }
}
