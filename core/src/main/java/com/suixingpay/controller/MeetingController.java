package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Meeting;
import com.suixingpay.response.Response;
import com.suixingpay.service.MeetingService;
import com.suixingpay.service.UserService;
import com.suixingpay.util.HttpUtil;
import com.suixingpay.util.TokenUtil;
import com.suixingpay.vo.ButlerUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private UserService userService;
    @Autowired
    private HttpUtil httpUtil;
    //添加会议（v5级用户以上）
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response add(@RequestBody @Validated Meeting meeting) {
        //调用校验时间方法
        String checkTimeResult = checkTime(meeting);
        if (checkTimeResult.equals("时间正确")) {
            //执行添加
            Integer result = meetingService.addMeeting(meeting);
            if (result == 1) {
                return Response.getInstance(CodeEnum.SUCCESS);
            }
            return Response.getInstance(CodeEnum.FAIL);
        }

        return Response.getInstance(CodeEnum.FAIL, checkTimeResult);
    }


    //添加会议（管理员）
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public Response addAdmin(@RequestBody @Validated Meeting meeting) {
        //调用校验时间方法
        String checkTimeResult = checkTime(meeting);
        if (checkTimeResult.equals("时间正确")) {
            String code = meeting.getReferralCode();
            Integer reCode=userService.getUserIdByReferCode(code);
            //如果推荐码不存在，那么用户id为空,将会议创建人id设为0
            if(reCode==null){
                meeting.setCreateUserId(0);
            }
            //如果推荐码存在，将会议创建人id设为推荐码人id
            meeting.setCreateUserId(reCode);
            //执行添加
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
        if (meeting.getId() == null) {
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


    //待审批，同意，驳回修改会议审批状态
    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public Response approve(@RequestParam("id") Integer id, @RequestParam("statusNumber") Integer statusNumber) {
        if (id == null || statusNumber == null) {
            return Response.getInstance(CodeEnum.FAIL, "参数为空");
        }
        meetingService.updateStatusById(id, statusNumber);
        return Response.getInstance(CodeEnum.SUCCESS);
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
        //从token中获取用户id
//        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);
//        if(token==null||token==""){
//            return "未登录！";
//        }
//        ButlerUserVO userVO = userService.parseUser(token);
//        Integer userId = userVO.getId();
//        //获取当前用户等级
//        String levelNumString =userVO.getLevelNum();
//        Integer levelNum = Integer.parseInt(levelNumString);
//        //如果当前用户等级不够，不能操作添加，修改功能
//        if(levelNum<5){
//            return "该用户无此功能权限！";
//        }
        Integer userId=1;
        meeting.setCreateUserId(userId);
        if ((signUpEndTime.after(startTime) || signUpEndTime.before(new Date()))) {
            return "会议报名截止时间选择不正确！";
        }
        if (startTime.before(new Date())) {
            return "会议开始时间选择不正确！";
        }
        return "时间正确";
    }

}
