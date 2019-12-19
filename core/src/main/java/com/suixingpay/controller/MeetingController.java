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


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response end(@RequestBody @Validated Meeting meeting) {
        //报名开始时间
        Date signUpStartTime = meeting.getSignUpStartTime();
        //报名截止时间
        Date signUpEndTime = meeting.getSignUpEndTime();
        //会议开始时间
        Date startTime = meeting.getStartTime();
        long startTimeHaoMiao = startTime.getTime();
        //前台获取会议时长，小时
        double durationShi = meeting.getDurationShi();
        //将小时转为毫秒数
        Integer durationHaoMiao = (int) (durationShi * 60 * 60 * 1000);
        //将小时转为秒数
        Integer durationMiao = (int) (durationShi * 60 * 60);
        //会议结束时间毫秒数
        long endTimeMiao = startTimeHaoMiao + durationHaoMiao.longValue();
        //会议创建时间
        meeting.setCreateTime(new Date());
        //前台获取会议时长，秒
        meeting.setDurationMiao(durationMiao);
        //会议结束时间
        meeting.setEndTime(new Date(endTimeMiao));
        //假的用户id
        meeting.setCreateUserId(1);
        if (signUpStartTime.after(startTime)) {
            return Response.getInstance(CodeEnum.FAIL, "会议报名开始时间选择不正确！");
        }
        if ((signUpEndTime.after(startTime) || signUpEndTime.before(signUpStartTime))) {
            return Response.getInstance(CodeEnum.FAIL, "会议报名截止时间选择不正确！");
        }
        if (startTime.before(new Date())) {
            return Response.getInstance(CodeEnum.FAIL, "会议开始时间选择不正确！");
        }
        Integer result = meetingService.addMeeting(meeting);
        if (result == 1) {
            return Response.getInstance(CodeEnum.SUCCESS);
        }
        return Response.getInstance(CodeEnum.FAIL);
    }

}
