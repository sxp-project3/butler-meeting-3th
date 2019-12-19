package com.suixingpay.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
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
    @NotBlank(message = "会议类型 为空！")
    private String type;
    //是否收费
    @NotNull(message = "是否收费 为空！")
    private Integer ifFee;
    //会议名称
    @NotBlank(message = "会议名称为空！")
    private String name;
    //主办方
    @NotBlank(message = "主办方为空！")
    private String host;
    //报名开始时间
    @NotNull(message = "报名开始时间为空！")
    private Date signUpStartTime;
    //报名截止时间
    @NotNull(message = "报名截止时间为空！")
    private Date signUpEndTime;
    //会议开始时间
    @NotNull(message = "会议开始时间为空！")
    private Date startTime;
    //会议结束时间
    private Date endTime;
    //会议时长（小时）
    @DecimalMin("0")
    private double durationShi;
    //会议时长（秒）
    private Integer durationMiao;
    //会议地点
    @NotBlank(message = "会议地点为空！")
    private String place;
    //详细地址
    @NotBlank(message = "详细地址为空！")
    private String detailAddress;
    //会议描述
    @NotBlank(message = "会议描述为空！")
    private String description;
    //会议当前状态
    private Integer status;
    //建立会议用户id
    private Integer createUserId;
    //建立会议时间
    private Date createTime;

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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}