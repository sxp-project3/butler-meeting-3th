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
import com.suixingpay.vo.SignUpVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.aspectj.apache.bcel.classfile.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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
//        meetingMap.put("province", meeting.getPlaceCity());
//        meetingMap.put("city", meeting.getPlaceCounty());

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
                map.put("province", butlerUser.getProvince());
                map.put("city", butlerUser.getCity());
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
//        meetingMap.put("province", meeting.getPlaceCity());
//        meetingMap.put("city", meeting.getPlaceCounty());
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
                if (sign1.getIsSignup() == 1 ){
                    String SignUpTime = sdf.format(sign1.getSignupTime());
                    map.put("IsSignUp", SignUpTime);
                }

                //通过用户id查询用户信息
                ButlerUser butlerUser = butlerSubordinatesServcie.selectByid(sign.getUserId());

                //装结果准备返回
                map.put("province", butlerUser.getProvince());
                map.put("city", butlerUser.getCity());
                map.put("Code", butlerUser.getReferralCode());
                map.put("userName", butlerUser.getName());
                map.put("telePhone", butlerUser.getTelephone());

                list1.add(map);
            }
        }
        meetingMap.put("SignInList", list1);
        return Response.getInstance(CodeEnum.SUCCESS, meetingMap);
    }

    @RequestMapping(value = "exportSignUpInfo")
    public void ExportSignUpInfo(@RequestBody Sign sign, HttpServletResponse response) throws IOException {

        LOGGER.info("签到信息接口接收的参数为[{}]", sign.getMeetingId());

        SignUpVo signUpVo = new SignUpVo();
        //接收前端参数
        Integer meetingId = sign.getMeetingId();

        //定义一个Map用于装结果
        List<SignUpVo> list1 = new ArrayList<>();



        //通过会议id查询会议信息
        Meeting meeting = meetingKjService.getOne(meetingId);

        //查询出当前会议的签到总人数
        int signUpSum = signService.selectCountSignIn(meetingId);

//        ButlerUser butlerUser = butlerSubordinatesServcie.selectByid(sign.getUserId());

        //通过用户id查询出报名、签到信息
//        Sign sign1 = signService.selectWithOutIdAndUserId(sign);

        log.info(meeting.getName()+"签到总人数"+signUpSum);
        //判断当前会议id是否存在

        signUpVo.setPlaceProvince(meeting.getPlaceProvince());
        signUpVo.setPlaceCity(meeting.getPlaceCity());
        signUpVo.setSignUpSum(signUpSum);

        //通过会议id查询出当前会议下的所有用户id
        List<Integer> list = signService.selectIdByMeeting(sign);

        for (int i = 0;i < list.size();i++){

            Map<String, Object> map = new HashMap<>();
            //每一次获取到一个用户id去查询
            sign.setUserId(list.get(i));

            //通过用户id查询出报名、签到信息
            Sign sign1 = signService.selectWithOutIdAndUserId(sign);

            //判断已签到，才有签到时间
            if (sign1.getIsSignup() == 1){
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String str = sdf.format(sign1.getSignupTime());
                signUpVo.setSignupTime(sign1.getSignupTime());
                signUpVo.setIsSignin(sign1.getIsSignin());


                //通过用户id查询用户信息
                ButlerUser butlerUser = butlerSubordinatesServcie.selectByid(sign.getUserId());

                //装结果准备返回
                signUpVo.setReferralCode(butlerUser.getReferralCode());
                signUpVo.setName(butlerUser.getName());
                signUpVo.setTelephone(butlerUser.getTelephone());

                list1.add(signUpVo);
            }
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("获取会议信息");
        HSSFRow row = null;
        row = sheet.createRow(0); //创建第一个单元格
        row.setHeight((short) (27 * 20));
        CellStyle cellStyle = wb.createCellStyle();
        row.createCell(0).setCellValue("报名信息"); //为第一行单元格设值
        //HSSFCell cell1 = row.createCell(0);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //居中
        //合并单元格  CellRangeAddress(起始行号，终止行号， 起始列号，终止列号）
        CellRangeAddress rowRegion = new CellRangeAddress(0, 0, 0, 9);
        sheet.addMergedRegion(rowRegion);
        //设置表头
        row = sheet.createRow(1);
        row.setHeight((short) (23 * 20)); //设置行高
        row.createCell(0).setCellValue("推荐码"); //为第一个单元格设值
        row.createCell(1).setCellValue("手机号"); //为第二个单元格设值
        row.createCell(2).setCellValue("姓名");
        row.createCell(3).setCellValue("落地省");
        row.createCell(4).setCellValue("落地市");
        row.createCell(5).setCellValue("报名时间");
        row.createCell(6).setCellValue("是否参会签到");


        for (int i = 0; i < list1.size(); i++) {
            row = sheet.createRow(i + 2);
            row.createCell(0).setCellValue(signUpVo.getReferralCode());
            row.createCell(1).setCellValue(signUpVo.getTelephone());
            row.createCell(2).setCellValue(signUpVo.getName());
            row.createCell(3).setCellValue(signUpVo.getPlaceProvince());
            row.createCell(4).setCellValue(signUpVo.getPlaceCity());
//            row.createCell(5).setCellValue(signUpVo.getSignupTime());
//            row.createCell(6).setCellValue(signUpVo.getIsSignin());



            //   row.createCell(4).setCellValue(meeting.getMeetingStartTime());
            //设置单元格时间格式
            //  CellStyle cellStyle = wb.createCellStyle();
            HSSFCell cell = row.createCell(5);
            HSSFDataFormat format = wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellValue(signUpVo.getSignupTime());
            cell.setCellStyle(cellStyle);

            if (signUpVo.getIsSignin() == 1){
                row.createCell(6).setCellValue("是");
            }else if (signUpVo.getIsSignin() == 0){
                row.createCell(6).setCellValue("否");
            }
        }
        sheet.setDefaultRowHeight((short) (17 * 20));
        //列宽自适应
        for (int i = 0; i <= 9; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment;filename=signUpInfo.xls"); //默认Excel名称
        wb.write(os);
        os.flush();
        os.close();


    }

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



