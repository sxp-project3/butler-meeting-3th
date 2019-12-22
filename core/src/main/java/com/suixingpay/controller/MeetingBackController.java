package com.suixingpay.controller;

import com.github.pagehelper.PageInfo;
import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.Meeting;
import com.suixingpay.query.SearchMeetingParamQuery;
import com.suixingpay.response.Response;
import com.suixingpay.service.MeetingKjService;
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
 * @Date: 2019/12/20
 */
@RestController
@RequestMapping("/meeting-back")
@Slf4j
public class MeetingBackController {
    @Autowired
    private MeetingKjService meetingKjService;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Response detail(@RequestParam(value = "id") String meetingIdString) {
        Integer meetingId = Integer.parseInt(meetingIdString);
        Meeting meeting = meetingKjService.getOne(meetingId);
        Map<String, Object> result = new HashMap<>();
        result.put("meeting", meeting);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, result);

        return response;
    }

    @RequestMapping(value = "/search-list", method = RequestMethod.POST)
    public Response searchList(@RequestBody SearchMeetingParamQuery searchMeetingParamQuery) {
        List<Meeting> meetings = meetingKjService.searchMeeting(searchMeetingParamQuery);
        PageInfo<Meeting> page = new PageInfo<>(meetings);
        Response<Map<String, HashMap>> response = Response.getInstance(CodeEnum.SUCCESS, page);

        return response;
    }
}
