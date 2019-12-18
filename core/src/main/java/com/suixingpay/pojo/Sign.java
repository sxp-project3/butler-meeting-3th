package com.suixingpay.pojo;

import java.util.Date;

public class Sign {
    private Integer id;

    private Integer userId;

    private Integer meetingId;

    private Date signupTime;

    private Date signinTime;

    private Integer isSignup;

    private Integer isSignin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Date getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Date signupTime) {
        this.signupTime = signupTime;
    }

    public Date getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(Date signinTime) {
        this.signinTime = signinTime;
    }

    public Integer getIsSignup() {
        return isSignup;
    }

    public void setIsSignup(Integer isSignup) {
        this.isSignup = isSignup;
    }

    public Integer getIsSignin() {
        return isSignin;
    }

    public void setIsSignin(Integer isSignin) {
        this.isSignin = isSignin;
    }
}