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
    public Response end(@RequestBody Meeting meeting) {
        //报名开始时间
        Date signUpStartTime= meeting.getSignUpStartTime();
        System.out.println(new Date());
        long miao= signUpStartTime.getTime();
       //报名截止时间
        Date signUpEndTime=meeting.getSignUpEndTime();
        //会议开始时间
        Date startTime=meeting.getStartTime();
        //前台获取会议时长，小时
        double durationShi=meeting.getDurationShi();
        //将小时转为毫秒数
        double durationMiao =durationShi*60*60;
        Integer durationMiao1=(int)durationMiao;
        //会议结束时间毫秒数
        double endTimeMiao= (long)miao+durationMiao;
        long endTimeMiao1 = new Double(endTimeMiao).longValue();
        //会议创建时间
        meeting.setCreateTime(meeting.getCreateTime());
        //前台获取会议时长，秒
        meeting.setDurationMiao(durationMiao1);
        //会议结束时间
        meeting.setEndTime(new Date(endTimeMiao1));
        //假的用户id
        meeting.setCreateUserId(1);
        if(signUpStartTime.after(startTime)){
            return Response.getInstance(CodeEnum.FAIL,"会议报名开始时间选择不正确！");
        }
        if((signUpEndTime.after(startTime)&&signUpEndTime.before(signUpStartTime))){
            return Response.getInstance(CodeEnum.FAIL,"会议报名截止时间选择不正确！");
        }
        if(startTime.before(new Date())){
            return Response.getInstance(CodeEnum.FAIL,"会议开始时间选择不正确！");
        }
        meetingService.addMeeting(meeting);
        return Response.getInstance(CodeEnum.SUCCESS);
    }

}
