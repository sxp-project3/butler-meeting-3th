package com.suixingpay.controller;

import com.suixingpay.pojo.Meeting;
import com.suixingpay.response.Response;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void end(@RequestBody @Validated  Meeting meeting) {
        //报名开始时间
        Date signUpStartTime= meeting.getSignUpStartTime();
       long miao= signUpStartTime.getTime();

        //报名截止时间
        Date signUpEndTime=meeting.getSignUpEndTime();
        //会议开始时间
        Date startTime=meeting.getStartTime();
        //会议结束时间
        //Date endTime=meeting.getEndTime();
        //会议创建时间
        Date createTime=meeting.getCreate_time();
        //当前时间
        Date nowTime =new Date();

        double durationShi=meeting.getDurationShi();
        double durationMiao =durationShi*60*60;
        Integer durationMiao1=(int)durationMiao;
        double a= (long)miao+durationMiao;
        long l = new Double(a).longValue();
        Date endTime= new Date(l);

    }

}
