package com.suixingpay.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

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
    //报名截止时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signUpEndTime;
    //会议开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(message = "会议开始时间为空！")
    private Date startTime;

    //会议时长（小时）
    @DecimalMin("0")
    private double duration;
    //会议地点省
    private String placeProvince;
    //会议地点市
    private String placeCity;
    //会议地点县
    private String placeCounty;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double durationShi) {
        this.duration = durationShi;
    }

    public String getPlaceProvince() {
        return placeProvince;
    }

    public void setPlaceProvince(String placeProvince) {
        this.placeProvince = placeProvince;
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public void setPlaceCity(String placeCity) {
        this.placeCity = placeCity;
    }

    public String getPlaceCounty() {
        return placeCounty;
    }

    public void setPlaceCounty(String placeCounty) {
        this.placeCounty = placeCounty;
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
