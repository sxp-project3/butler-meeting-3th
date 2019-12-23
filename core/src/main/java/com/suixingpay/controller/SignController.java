package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.pojo.Meeting;
import com.suixingpay.pojo.Sign;
import com.suixingpay.response.Response;
import com.suixingpay.service.ButlerSubordinatesServcie;
import com.suixingpay.service.MeetingKjService;
import com.suixingpay.service.SignService;

import com.suixingpay.service.UserService;
import com.suixingpay.util.HttpUtil;
import com.suixingpay.util.TokenUtil;
import com.suixingpay.util.Utils;
import com.suixingpay.vo.ButlerUserVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author 段思宇，黄宇萧
 * @program: butler-meeting-3th
 * @description: 报名、签到控制器
 * @date 2019-12-18
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class SignController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignController.class);

    @Autowired
    private SignService signService;

    @Autowired
    private MeetingKjService meetingKjService;

    @Autowired
    private ButlerSubordinatesServcie butlerSubordinatesServcie;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpUtil httpUtil;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Response signUpActive(@RequestBody Sign sign) {
        //接参
        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);
        ButlerUserVO userVO = userService.parseUser(token);

        //通过token传入的参数会议id
        Integer userID = userVO.getId();
        sign.setUserId(userID);

        LOGGER.info("报名功能接收的参数为[{},{}]", userVO.getId(), sign.getMeetingId());

        //查询出当前会议下所有用户id用作报名判断
        List<Integer> list = signService.selectIdByMeeting(sign);

        //接收前端传入的参数会议id
        Integer userId = sign.getUserId();
        Integer meetingId = sign.getMeetingId();
        Date date = new Date();

        //用户参数判空
        if (userId == null) {
            LOGGER.info("报名失败，不存在此用户");
            return Response.getInstance(CodeEnum.FAIL, "请选择正确的用户");
        }

        //会议参数判空
        if (meetingId == null) {
            LOGGER.info("报名失败，不存在此会议");
            return Response.getInstance(CodeEnum.FAIL, "请选择正确的会议");
        }

        //判断是否已超过报名时间，通过会议id查询报名截止时间
        Meeting meeting = meetingKjService.getOne(meetingId);
        if (meeting == null) {
            LOGGER.info("不存在此会议，无法报名");
            return Response.getInstance(CodeEnum.FAIL, "不存在此会议，无法报名");
        } else if (meeting.getSignUpEndTime().before(date)) {
            LOGGER.info("已过报名时间");
            return Response.getInstance(CodeEnum.FAIL, "已过报名时间");
        }

        //判断是否存在此用户
        ButlerUser butlerUser = butlerSubordinatesServcie.selectByid(userId);
        if (butlerUser == null){
            LOGGER.info("不存在此用户，无法支持报名");
            return Response.getInstance(CodeEnum.FAIL, "不存在此用户，无法报名");
        }

        //判断是否已经报名会议
        if (list.contains(userId)) {
            LOGGER.info("已报名，不可重复报名");
            return Response.getInstance(CodeEnum.FAIL, "已报名，不可重复报名");
        }

        //可直接报名
        if (!list.contains(userId)) {
            sign.setUserId(userId);
            sign.setMeetingId(meetingId);
            sign.setSignupTime(date);
            sign.setIsSignup(1);
            signService.signUpActive(sign);
            LOGGER.info("报名成功");
        }
        return Response.getInstance(CodeEnum.SUCCESS, "报名成功");
    }


    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public Response selectIdByMeeting(@RequestBody Sign sign) {

        //接参
        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);
        ButlerUserVO userVO = userService.parseUser(token);

        //通过token传入的参数会议id
        Integer userID = userVO.getId();
        sign.setUserId(userID);

        LOGGER.info("报名功能接收的参数为[{},{}]", userVO.getId(), sign.getMeetingId());

        //查询出当前会议下所有用户id用作报名判断
        List<Integer> list = signService.selectIdByMeeting(sign);

        //接收前端传入的参数会议id
        Integer userId = sign.getUserId();
        Integer meetingId = sign.getMeetingId();
        Date date = new Date();

        //用户参数判空
        if (userId == null) {
            LOGGER.info("签到失败，不存在此用户");
            return Response.getInstance(CodeEnum.FAIL, "请选择正确的用户");
        }

        //会议参数判空
        if (meetingId == null) {
            LOGGER.info("签到失败，不存在此会议");
            return Response.getInstance(CodeEnum.FAIL, "请选择正确的会议");
        }

        //判断是否有当前会议
        Meeting meeting = meetingKjService.getOne(meetingId);
        if (meeting == null) {
            LOGGER.info("不存在此会议，无法签到");
            return Response.getInstance(CodeEnum.FAIL, "不存在此会议，无法签到");
        }

        //判断是否存在此用户
        ButlerUser butlerUser = butlerSubordinatesServcie.selectByid(userId);
        if (butlerUser == null){
            LOGGER.info("不存在此用户，无法支持签到");
            return Response.getInstance(CodeEnum.FAIL, "不存在此用户，无法签到");
        }

        //获取会议开始时间
        Date dateInfo = meeting.getStartTime();

        //获取会议的时间
        double meetingHour = meeting.getDuration();

        //将会议开始时间转换成时间戳
        long startTime = dateInfo.getTime();

        //获取当前时间的时间戳
        long nowTime = date.getTime();

        long sumTime = (long) meetingHour * 3600 * 1000 + 24 * 3600 * 1000 + startTime;
        System.out.println(nowTime);
        System.out.println(sumTime);

        //是否可以签到的判断
        if (nowTime > sumTime){
            LOGGER.info("已过签到时间");
            return Response.getInstance(CodeEnum.FAIL, "已过签到时间");
        }


        //获取到签到状态做判断是否已经签到
        Sign sign1 = signService.selectWithOutIdAndUserId(sign);

        //如果当前用户没有报名直接签到的情况
        if (sign1 == null) {
            //未报名的签到，增加数据
            sign.setUserId(userId);
            sign.setMeetingId(meetingId);
            sign.setSigninTime(date);
            sign.setIsSignin(1);
            LOGGER.info("未报名的签到");
            signService.insertSignIn(sign);

            return Response.getInstance(CodeEnum.SUCCESS, "签到成功");
        } else if (sign1.getIsSignin() == 1) {
            LOGGER.info("已签到，不可以重复签到");
            return Response.getInstance(CodeEnum.FAIL, "已签到，不可重复签到");
        }

        //当前用户已报名的签到情况
        if (list.contains(userId)) {
            LOGGER.info("已报名的签到");
            signService.updateSignIn(sign);
        }
        return Response.getInstance(CodeEnum.SUCCESS, "签到成功");
    }

    @RequestMapping(value = "/selectSignUp", method = RequestMethod.POST)
    public Response SignUpInfo(@RequestBody Sign sign) {

        LOGGER.info("报名信息接口接收的参数为[{}]", sign.getMeetingId());

        //接收前端参数
        Integer meetingId = sign.getMeetingId();

        //定义一个Map用于装结果
        Map<String, Object> meetingMap = new HashMap<>();
        List<Map<String, Object>> list1 = new ArrayList<>();


        //接收会议id的参数判空
        if (meetingId == null){
            LOGGER.info("没有接收到会议id");
            return Response.getInstance(CodeEnum.FAIL,"没有接收到会议id");
        }

        //通过会议id查询会议信息
        Meeting meeting = meetingKjService.getOne(meetingId);

        //查询出当前会议的报名总人数
        int signUpSum = signService.selectCountSignUp(meetingId);

        log.info(meeting.getName()+"报名总人数"+signUpSum);

        //判断当前会议id是否存在
        if (meeting == null){
            LOGGER.info("不存在的会议");
            return Response.getInstance(CodeEnum.FAIL,"不存在此会议");
        }
        meetingMap.put("signUpSum", signUpSum);
        meetingMap.put("province", meeting.getPlaceProvince());
        meetingMap.put("city", meeting.getPlaceCity());

        //通过会议id查询出当前会议下的所有用户id
        List<Integer> list = signService.selectIdByMeeting(sign);

        //判断当前会议有没有用户报名
        if (list.size() == 0){
            //给前端提示当前会议没有任何报名
            meetingMap.put("signUpCount", 0);
        }
        for (int i = 0;i < list.size();i++){

            Map<String, Object> map = new HashMap<>();
            //每一次获取到一个用户id去查询
            sign.setUserId(list.get(i));

            //通过用户id查询出报名、签到信息
            Sign sign1 = signService.selectWithOutIdAndUserId(sign);

            //判断已报名，才有报名时间
            if (sign1.getIsSignup() ==1){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(sign1.getSignupTime());
                map.put("SignUpTime", str);
                map.put("IsSignIn", sign1.getIsSignin());


                //通过用户id查询用户信息
                ButlerUser butlerUser = butlerSubordinatesServcie.selectByid(sign.getUserId());

                //装结果准备返回
                map.put("Code", butlerUser.getReferralCode());
                map.put("userName", butlerUser.getName());
                map.put("telePhone", butlerUser.getTelephone());

                list1.add(map);
            }
        }
        meetingMap.put("SignUpList", list1);
        return Response.getInstance(CodeEnum.SUCCESS, meetingMap);
    }

    @RequestMapping(value = "/selectSignIn",method = RequestMethod.POST)
    public Response SignInInfo(@RequestBody Sign sign) {

        LOGGER.info("签到信息接口接收的参数为[{}]", sign.getMeetingId());

        //接收前端参数
        Integer meetingId = sign.getMeetingId();

        //定义一个Map用于装结果
        Map<String, Object> meetingMap = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list1 = new ArrayList<>();


        //接收会议id的参数判空
        if (meetingId == null){
            LOGGER.info("没有接收到会议id");
            return Response.getInstance(CodeEnum.FAIL,"没有接收到会议id");
        }

        //通过会议id查询会议信息
        Meeting meeting = meetingKjService.getOne(meetingId);

        //查询出当前会议的签到总人数
        int signInSum = signService.selectCountSignIn(meetingId);

        log.info(meeting.getName()+"签到总人数"+signInSum);
        //判断当前会议id是否存在
        if (meeting == null){
            LOGGER.info("不存在的会议");
            return Response.getInstance(CodeEnum.FAIL,"不存在此会议");
        }
        meetingMap.put("province", meeting.getPlaceProvince());
        meetingMap.put("city", meeting.getPlaceCity());
        meetingMap.put("signInSum", signInSum);

        //通过会议id查询出当前会议下的所有用户id
        List<Integer> list = signService.selectIdByMeeting(sign);

        //判断当前会议有没有用户签到
        if (list.size() == 0){
            meetingMap.put("signInCount", 0);
        }
        for (int i = 0;i < list.size();i++){

            Map<String, Object> map = new HashMap<>();
            //每一次获取到一个用户id去查询
            sign.setUserId(list.get(i));

            //通过用户id查询出报名、签到信息
            Sign sign1 = signService.selectWithOutIdAndUserId(sign);

            //判断已签到，才有签到时间
            if (sign1.getIsSignin() == 1){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(sign1.getSigninTime());
                map.put("SignInTime", str);
                map.put("IsSignUp", sign1.getIsSignup());
                if (sign1.getIsSignup() == 1){
                    String SignUpTime = sdf.format(sign1.getSignupTime());
                    map.put("IsSignUp", SignUpTime);
                }

                //通过用户id查询用户信息
                ButlerUser butlerUser = butlerSubordinatesServcie.selectByid(sign.getUserId());

                //装结果准备返回
                map.put("Code", butlerUser.getReferralCode());
                map.put("userName", butlerUser.getName());
                map.put("telePhone", butlerUser.getTelephone());

                list1.add(map);
            }
        }
        meetingMap.put("SignInList", list1);
        return Response.getInstance(CodeEnum.SUCCESS, meetingMap);
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
