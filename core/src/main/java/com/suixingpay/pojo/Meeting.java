package com.suixingpay.pojo;

import java.util.Date;

/**
 * @author zhangleying
 * @version 1.0
 * @date 2019/12/18 14:50
 */
public class Meeting {
    //会议id
    private Integer id;
    //会议类型
    private String type;
    //是否收费
    private Integer ifFee;
    //会议名称
    private String name;
    //主办方
    private String host;
    //报名开始时间
    private Date signUpStartTime;
    //报名截止时间
    private Date signUpEndTime;
    //会议开始时间
    private Date startTime;
    //会议结束时间
    private Date endTime;
    //会议时长（小时）
    private double durationShi;
    //会议时长（秒）
    private Integer durationMiao;
    //会议地点
    private String place;
    //详细地址
    private String detailAddress;
    //会议描述
    private String description;
    //会议当前状态
    private Integer status;
    //建立会议用户id
    private Integer create_user_id;
    //建立会议时间
    private Date create_time;

    public Meeting() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIfFee() {
        return ifFee;
    }

    public void setIfFee(Integer ifFee) {
        this.ifFee = ifFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getSignUpStartTime() {
        return signUpStartTime;
    }

    public void setSignUpStartTime(Date signUpStartTime) {
        this.signUpStartTime = signUpStartTime;
    }

    public Date getSignUpEndTime() {
        return signUpEndTime;
    }

    public void setSignUpEndTime(Date signUpEndTime) {
        this.signUpEndTime = signUpEndTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getDurationShi() {
        return durationShi;
    }

    public void setDurationShi(double durationShi) {
        this.durationShi = durationShi;
    }

    public Integer getDurationMiao() {
        return durationMiao;
    }

    public void setDurationMiao(Integer durationMiao) {
        this.durationMiao = durationMiao;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreate_user_id() {
        return create_user_id;
    }

    public void setCreate_user_id(Integer create_user_id) {
        this.create_user_id = create_user_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
