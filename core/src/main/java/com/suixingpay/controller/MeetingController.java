package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Meeting;
import com.suixingpay.response.Response;
import com.suixingpay.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/18 15:38
 */
@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    //添加会议
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response add(@RequestBody @Validated Meeting meeting) {
        String checkTimeResult = checkTime(meeting);
        if (checkTimeResult.equals("时间正确")) {
            Integer result = meetingService.addMeeting(meeting);
            if (result == 1) {
                return Response.getInstance(CodeEnum.SUCCESS);
            }
            return Response.getInstance(CodeEnum.FAIL);
        }

        return Response.getInstance(CodeEnum.FAIL, checkTimeResult);
    }


    //根据会议id修改会议信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(@RequestBody @Validated Meeting meeting) {
        if(meeting.getId()==null){
            return Response.getInstance(CodeEnum.FAIL, "会议为空");
        }
        String checkTimeResult = checkTime(meeting);
        if (checkTimeResult.equals("时间正确")) {
            Integer result = meetingService.updateMeetingById(meeting);
            if (result == 1) {
                return Response.getInstance(CodeEnum.SUCCESS);
            }
            return Response.getInstance(CodeEnum.FAIL);
        }

        return Response.getInstance(CodeEnum.FAIL, checkTimeResult);
    }


    //改方法用于添加，修改会议功能的时间校验和添加给meeting实体加值
    public String checkTime(Meeting meeting) {
        //报名截止时间
        Date signUpEndTime = meeting.getSignUpEndTime();
        //会议开始时间
        Date startTime = meeting.getStartTime();
        //会议创建时间
        meeting.setCreateTime(new Date());
        //前台获取会议时长
        meeting.setDuration(meeting.getDuration());
        //假的用户id
        meeting.setCreateUserId(1);
        if ((signUpEndTime.after(startTime) ||signUpEndTime.before(new Date()))) {
            return "会议报名截止时间选择不正确！";
        }
        if (startTime.before(new Date())) {
            return "会议开始时间选择不正确！";
        }
        return "时间正确";
    }

}
