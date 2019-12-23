package com.suixingpay.vo;

import java.util.Date;

public class SignUpVo {

    private String name;

    private String telephone;

    private String referralCode;

    private String placeProvince;

    private String placeCity;

    private Date signupTime;

    private Date signinTime;

    private Integer isSignup;

    private Integer isSignin;

    private Integer signUpSum;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
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

    public Integer getSignUpSum() {
        return signUpSum;
    }

    public void setSignUpSum(Integer signUpSum) {
        this.signUpSum = signUpSum;
    }
}
