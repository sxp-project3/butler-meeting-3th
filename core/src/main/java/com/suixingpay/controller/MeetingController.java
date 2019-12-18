package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
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
    public Response end(@RequestBody @Validated  Meeting meeting) {
        //报名开始时间
        Date signUpStartTime= meeting.getSignUpStartTime();
       long miao= signUpStartTime.getTime();
       //报名截止时间
        Date signUpEndTime=meeting.getSignUpEndTime();
        //会议开始时间
        Date startTime=meeting.getStartTime();
        //会议创建时间
        Date createTime=meeting.getCreateTime();
        //当前时间
        Date nowTime =new Date();
        //前台获取会议时长，小时
        double durationShi=meeting.getDurationShi();
        //将小时转为毫秒数
        double durationMiao =durationShi*60*60;
        Integer durationMiao1=(int)durationMiao;
        //会议结束时间毫秒数
        double endTimeMiao= (long)miao+durationMiao;
        long endTimeMiao1 = new Double(endTimeMiao).longValue();
        //会议结束时间
        Date endTime= new Date(endTimeMiao1);
        if(signUpStartTime.after(startTime)){
            return Response.getInstance(CodeEnum.FAIL,"会议报名开始时间选择不正确！");
        }
        if((signUpEndTime.after(startTime)&&signUpEndTime.after(signUpStartTime))){
            return Response.getInstance(CodeEnum.FAIL,"会议报名截止时间选择不正确！");
        }
        if(startTime.before(nowTime)){
            return Response.getInstance(CodeEnum.FAIL,"会议开始时间选择不正确！");
        }

        return Response.getInstance(CodeEnum.SUCCESS);
    }

}
