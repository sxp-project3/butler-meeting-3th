package com.suixingpay.pojo;

import java.util.Date;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户下属表
 * <p>
 * Created by Jalr4ever on 2019/12/18.
 */

public class ButlerSubordinates {

    private Integer id;

    private Integer userId;

    private Integer subordinatesId;

    private Date createTime;

    private Date updateTime;

    private String isDelete;

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

    public Integer getSubordinatesId() {
        return subordinatesId;
    }

    public void setSubordinatesId(Integer subordinatesId) {
        this.subordinatesId = subordinatesId;
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
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

}
