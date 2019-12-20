package com.suixingpay.service;

import com.suixingpay.pojo.Meeting;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/18 15:36
 */
public interface MeetingService {
    //插入一条会议，对应新建会议功能
    Integer addMeeting(Meeting Meeting);
    //根据会议id修改会议信息
    Integer updateMeetingById(Meeting Meeting);
}
