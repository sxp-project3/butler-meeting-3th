package com.suixingpay.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Meeting;
import com.suixingpay.pojo.Sign;
import com.suixingpay.query.SearchMeetingParamQuery;
import com.suixingpay.response.Response;
import com.suixingpay.service.ButlerSubordinatesServcie;
import com.suixingpay.service.MeetingKjService;
import com.suixingpay.service.SignService;
import com.suixingpay.service.UserService;
import com.suixingpay.takin.data.domain.PageImpl;
import com.suixingpay.takin.mybatis.domain.Pagination;
import com.suixingpay.util.HttpUtil;
import com.suixingpay.util.TokenUtil;
import com.suixingpay.vo.ButlerUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kongjian
 * @Date: 2019/12/18
 */
@RestController
@RequestMapping("/meetingDemo")
@Slf4j
public class MeetingDemoController {
    @Autowired
    private MeetingKjService meetingKjService;

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private SignService signService;

    @RequestMapping(value = "/validMeetings", method = RequestMethod.GET)
    public Response validMeeting(@RequestParam(value="pageNum", required = false) String pageNumString,
                                 @RequestParam(value="pageSize", required = false) String pageSizeString) {
        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);
        ButlerUserVO userVO = userService.parseUser(token);
        Integer userId = userVO.getId();
        Integer pageNum = Integer.parseInt(pageNumString);
        Integer pageSize = Integer.parseInt(pageSizeString);
        // PageHelper.startPage(pageNum, pageSize);
        Pagination pagination = new Pagination(0, 2);
        PageImpl page = meetingKjService.getValidMeeting(userId, pagination);
        // List<Meeting> meetings = meetingKjService.getValidMeeting(userId);
        // PageInfo<Meeting> page = new PageInfo<>(meetings);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, page);

        return response;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Response detail(@RequestParam(value = "id") String meetingIdString) {
        // 这里尚未完成，需要验证用户身份
        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);
        ButlerUserVO userVO = userService.parseUser(token);
        Integer userId = userVO.getId();

        Integer meetingId = Integer.parseInt(meetingIdString);
        Meeting meeting = meetingKjService.getOne(meetingId);
        Map<String, Object> result = new HashMap<>();
        result.put("meeting", meeting);

        Sign hasSign = new Sign();
        hasSign.setUserId(userId);
        hasSign.setMeetingId(meetingId);
        Sign signResult = signService.selectWithOutIdAndUserId(hasSign);
        if (signResult == null) {
            result.put("hasSignUp", 0);
        } else {
            result.put("hasSignUp", signResult.getIsSignup());
        }
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, result);

        return response;
    }

    @RequestMapping(value = "/my-create-list")
    public Response getMyCreateList(@RequestParam(value="pageNum", required = false) String pageNumString,
                                    @RequestParam(value="pageSize", required = false) String pageSizeString) {
        // 这里尚未完成，需要验证用户身份
        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);
        ButlerUserVO userVO = userService.parseUser(token);
        Integer userId = userVO.getId(); // 模拟用户id
        // log.info("userID:"+userId);
        Integer pageNum = Integer.parseInt(pageNumString);
        Integer pageSize = Integer.parseInt(pageSizeString);
        PageHelper.startPage(pageNum, pageSize);
        List<Meeting> meetings = meetingKjService.getMyMeetings(userId);
        PageInfo<Meeting> page = new PageInfo<>(meetings);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, page);

        return response;
    }
}
