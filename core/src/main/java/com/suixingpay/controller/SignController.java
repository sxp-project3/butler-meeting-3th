package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Sign;
import com.suixingpay.response.Response;
import com.suixingpay.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class SignController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    @Autowired
    private SignService signService;

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public Response signUpActive(@RequestBody Sign sign){

        Date date = new Date();

        //接参
        LOGGER.info("接收的参数为[{}]",sign.getUserId(),sign.getMeetingId());
        Integer userId = sign.getUserId();
        Integer meetingId = sign.getMeetingId();

        //用户参数判空
        if (userId == null){
            LOGGER.info("报名失败，不存在此用户");
            return Response.getInstance(CodeEnum.FAIL,"请选择正确的用户");
        }

        //会议参数判空
        if (meetingId == null){
            LOGGER.info("报名失败，不存在此会议");
            return Response.getInstance(CodeEnum.FAIL,"请选择正确的会议");
        }

        sign.setUserId(userId);
        sign.setMeetingId(meetingId);
        sign.setSignupTime(new Date());
        sign.setIsSignup(1);
        signService.signUpActive(sign);
        LOGGER.info("报名成功");
        return Response.getInstance(CodeEnum.SUCCESS,"报名成功");
    }


    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public Response selectIdByMeeting(@RequestBody Sign sign){

        Date date = new Date();

        //接参
        LOGGER.info("接收的参数为[{}]",sign.getUserId(),sign.getMeetingId());

        //查询出当前会议下所有用户用作签到判断
        List<Integer> list = signService.selectIdByMeeting(sign);

        //接收当前签到的用户id和活动id
        Integer userId = sign.getUserId();
        Integer meetingId = sign.getMeetingId();

        //已报名的签到，修改签到状态
        if (list.contains(userId)){
            LOGGER.info("已报名的签到");
            signService.updateSignIn(sign);
        }else {
            //未报名的签到，增加数据
            sign.setUserId(userId);
            sign.setMeetingId(meetingId);
            sign.setSigninTime(date);
            sign.setIsSignin(1);
            LOGGER.info("未报名的签到");
            signService.insertSignIn(sign);
        }
        return Response.getInstance(CodeEnum.SUCCESS,"签到成功");
    }


    
//    @RequestMapping(value = "/test",method = RequestMethod.POST)
//    public Sign test(){
//
//        Sign sign = new Sign();
//        sign.setUserId(1111);
//        sign.setMeetingId(11);
//
//        Sign signs = signService.selectWithOutIdAndUserId(sign);
//
//        return signs;
//    }
//    @RequestMapping(value = "/testCount",method = RequestMethod.POST)
//    public Response testCount(){
//        int meeting=11;
//        int i = signService.selectCountSignUp(meeting);
//        int y = signService.selectCountSignIn(meeting);
//
//        System.out.println("当前报名人数："+i+"会议id为："+meeting);
//        System.out.println("当前签到人数："+y+"会议id为："+meeting);
//
//        return Response.getInstance(CodeEnum.SUCCESS,"查询成功");
//
//    }



}
