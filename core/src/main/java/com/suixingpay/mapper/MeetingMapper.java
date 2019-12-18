package com.suixingpay.mapper;

import com.suixingpay.pojo.Meeting;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/18 15:33
 */
@Mapper
public interface MeetingMapper {
    //插入一条会议
    Integer insertMeeting(Meeting meeting);
}
