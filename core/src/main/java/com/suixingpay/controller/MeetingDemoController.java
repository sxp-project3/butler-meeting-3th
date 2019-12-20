package com.suixingpay.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Meeting;
import com.suixingpay.response.Response;
import com.suixingpay.service.MeetingKjService;
import com.suixingpay.service.UserService;
import com.suixingpay.util.HttpUtil;
import com.suixingpay.util.TokenUtil;
import com.suixingpay.vo.ButlerUserVO;
import com.suixingpay.vo.SearchMeetingParamVo;
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

    @RequestMapping(value = "/validMeetings", method = RequestMethod.GET)
    public Response validMeeting(@RequestParam(value="pageNum", required = false) String pageNumString,
                                 @RequestParam(value="pageSize", required = false) String pageSizeString) {
        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);
        ButlerUserVO userVO = userService.parseUser(token);
        log.info("userID:"+userVO.getId());
        // Integer userId = userVO.getId();
        Integer userId = 10001;
        Integer pageNum = Integer.parseInt(pageNumString);
        Integer pageSize = Integer.parseInt(pageSizeString);
        PageHelper.startPage(pageNum, pageSize);
        List<Meeting> meetings = meetingKjService.getValidMeeting(userId);
        PageInfo<Meeting> page = new PageInfo<>(meetings);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, page);

        return response;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Response detail(@RequestParam(value = "id") String meetingIdString) {
        // 这里尚未完成，需要验证用户身份
        Integer userId = 10001; // 模拟用户id

        Integer meetingId = Integer.parseInt(meetingIdString);
        Meeting meeting = meetingKjService.getOne(meetingId);
        Map<String, Object> result = new HashMap<>();
        result.put("meeting", meeting);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, result);

        return response;
    }

    @RequestMapping(value = "/my-create-list")
    public Response getMyCreateList(@RequestParam(value="pageNum", required = false) String pageNumString,
                                    @RequestParam(value="pageSize", required = false) String pageSizeString) {
        // 这里尚未完成，需要验证用户身份
        Integer userId = 10001; // 模拟用户id

        log.info(pageNumString);
        log.info(pageSizeString);
        Integer pageNum = Integer.parseInt(pageNumString);
        Integer pageSize = Integer.parseInt(pageSizeString);
        PageHelper.startPage(pageNum, pageSize);
        List<Meeting> meetings = meetingKjService.getValidMeeting(userId);
        PageInfo<Meeting> page = new PageInfo<>(meetings);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, page);

        return response;
    }

    @RequestMapping(value = "/search-list", method = RequestMethod.POST)
    public Response searchList(@RequestBody SearchMeetingParamVo searchMeetingParamVo) {
        // 这里尚未完成，需要验证用户身份
//        Integer userId = 10001; // 模拟用户id
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("createUserId", 10001);
//        params.put("ifFee", 1);
//        params.put("place", "北京");


//        Integer pageNum = Integer.parseInt(pageNumString);
//        Integer pageSize = Integer.parseInt(pageSizeString);
//        PageHelper.startPage(pageNum, pageSize);
        List<Meeting> meetings = meetingKjService.searchMeeting(searchMeetingParamVo);
        PageInfo<Meeting> page = new PageInfo<>(meetings);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, page);

        return response;
    }
}
