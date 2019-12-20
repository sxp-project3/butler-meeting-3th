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
     * @param meeting 会议实体
     * @return
     */
    @Override
    public Integer updateMeetingById(Meeting meeting) {
        Integer id = meeting.getId();
        Integer result = 0;
        Integer status = meetingMapper.selectOneById(id).getStatus();
        //如果会议当前审批状态为0或2，则修改后，审批状态改为待审批0
        if (status == 0 || status == 2) {
            meeting.setStatus(0);
            result = meetingMapper.updateMeetingById(meeting);
            return result;
        }
        //如果会议当前审批状态为1，则修改后，审批状态改为同意1
        if (status == 1) {
            meeting.setStatus(1);
            result = meetingMapper.updateMeetingById(meeting);
            return result;
        }
        return result;

    }

    @Override
    public void updateStatusById(Integer id, Integer status) {
        //如果管理员点击待审批，会议审批状态修改为0
        if (status == 0) {
            meetingMapper.updateStatusById(id, 0);
        }
        //如果管理员点击同意，会议审批状态修改为1
        if (status == 1) {
            meetingMapper.updateStatusById(id, 1);
        }
        //如果管理员点击驳回，会议审批状态修改为2
        if (status == 2) {
            meetingMapper.updateStatusById(id, 2);
        }

    }
}
