package com.suixingpay.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: kongjian
 * @Date: 2019/12/19
 */

public class SearchMeetingParamVo {
    // 鑫管家推荐码
    private String userPromoteCode;

    // 是否收费
    private Integer ifFee;

    // 会议开始日期起点
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date starttimeBegin;

    // 会议开始日期终点
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date starttimeEnd;

    // 所属分公司（地点省）
    private String placeProvince;

    // 会议类型
    private String type;

    // 发起类型（是否是管家创建）
    private Integer isUserCreate;

    // 审核状态
    private Integer status;

    // 会议状态
    private String meetingStat;

    // 创建用户id
    private Integer createUserId;

    public String getPlaceProvince() {
        return placeProvince;
    }

    public void setPlaceProvince(String placeProvince) {
        this.placeProvince = placeProvince;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Integer pageNum;

    private Integer pageSize;

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }


    public String getUserPromoteCode() {
        return userPromoteCode;
    }

    public void setUserPromoteCode(String userPromoteCode) {
        this.userPromoteCode = userPromoteCode;
    }

    public Integer getIfFee() {
        return ifFee;
    }

    public void setIfFee(Integer ifFee) {
        this.ifFee = ifFee;
    }

    public Date getStarttimeBegin() {
        return starttimeBegin;
    }

    public void setStarttimeBegin(Date starttimeBegin) {
        this.starttimeBegin = starttimeBegin;
    }

    public Date getStarttimeEnd() {
        return starttimeEnd;
    }

    public void setStarttimeEnd(Date starttimeEnd) {
        this.starttimeEnd = starttimeEnd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsUserCreate() {
        return isUserCreate;
    }

    public void setIsUserCreate(Integer isUserCreate) {
        this.isUserCreate = isUserCreate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMeetingStat() {
        return meetingStat;
    }

    public void setMeetingStat(String meetingStat) {
        this.meetingStat = meetingStat;
    }
}
