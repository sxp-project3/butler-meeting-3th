package com.suixingpay.mapper;

import com.suixingpay.pojo.Meeting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.jute.compiler.JString;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/18 15:33
 */
@Mapper
public interface MeetingMapper {
    //插入一条会议
    Integer insertMeeting(Meeting meeting);

    //根据会议id修改会议信息
    Integer updateMeetingById(Meeting meeting);

    // 获取我的可报名的会议
    List<Meeting> getListForFrontShow(@Param("nowDate") Date nowDate,
                                      @Param("createUserIds") List<Integer> createUserIds);

//    @Param("mapParams") HashMap mapParams);

    Meeting selectOneById(@Param("meetingId") Integer meetingId);

    List<Meeting> getMyCreateList(@Param("userId") Integer userId);

    List<Meeting> paramSearchList(@Param("params") Map<String, Object> params);

    //待审批，同意，驳回修改会议审批状态
    Integer updateStatusById(@Param("id") Integer id, @Param("status") Integer status);
}
