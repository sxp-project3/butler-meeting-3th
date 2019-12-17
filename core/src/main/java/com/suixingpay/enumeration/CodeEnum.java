package com.suixingpay.enumeration;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 响应状态码枚举
 * <p>
 * Created by Jalr4ever on 2019/12/17.
 */
public enum CodeEnum {

    SUCCESS("0", "成功","ok"),
    FAIL("-1", "服务器异常"),
    NOTLOGING("401","未登录");

    private String code;
    private String msg;
    private String ok;

    CodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    CodeEnum(String code, String msg, String ok) {
        this.code = code;
        this.msg = msg;
        this.ok = ok;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
