package com.suixingpay.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.suixingpay.pojo.ButlerUser;

import java.util.Date;

/**
 * @program: butler-meeting-3th
 * @description: 用户的显示层实体
 * <p>
 * Created by Jalr4ever on 2019/12/18.
 */
public class ButlerUserVO {
    private Integer id;

    private String name;

    private String telephone;

    private String account;

    private String password;

    private String rootUserId;

    private String leaderId;

    private String referralCode;

    private String levelNum;

    private String province;

    private String city;

    private String role;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private String isDelete;

    private String token;

    public ButlerUserVO() {

    }

    public ButlerUserVO(ButlerUser butlerUser, String token) {
        this.id = butlerUser.getId();
        this.name = butlerUser.getName();
        this.telephone = butlerUser.getTelephone();
        this.account = butlerUser.getAccount();
        this.password = butlerUser.getPassword();
        this.rootUserId = butlerUser.getRootUserId();
        this.leaderId = butlerUser.getLeaderId();
        this.referralCode = butlerUser.getReferralCode();
        this.levelNum = butlerUser.getLevelNum();
        this.province = butlerUser.getProvince();
        this.city = butlerUser.getCity();
        this.role = butlerUser.getRole();
        this.createTime = butlerUser.getCreateTime();
        this.updateTime = butlerUser.getUpdateTime();
        this.isDelete = butlerUser.getIsDelete();
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRootUserId() {
        return rootUserId;
    }

    public void setRootUserId(String rootUserId) {
        this.rootUserId = rootUserId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(String levelNum) {
        this.levelNum = levelNum;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
