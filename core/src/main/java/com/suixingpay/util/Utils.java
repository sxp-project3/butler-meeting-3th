package com.suixingpay.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName Utils
 *
 * @Description TODO
 * @Author luyun
 * @Date 2019/12/18 12:03
 * @Version 1.0
 **/

public class Utils {

    @Autowired
     private   HttpServletRequest httpServletRequest;

    /**
     * 功能描述: <判断是否为空>
     * 〈〉
     * @Param: [str]
     * @Return: boolean
     * @Author: luyun
     * @Date: 2019/12/18 12:03
     */
    public  boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;

        }
    }
    /**
     * 功能描述: <根据token获取token值>
     * 〈〉
     * @Param: [tokenName]
     * @Return: java.lang.String
     * @Author: luyun
     * @Date: 2019/12/18 12:05
     */
    public   String getToken(String tokenName){
        String token=httpServletRequest.getHeader(tokenName);
        if (tokenName==null){
            new RuntimeException("token为空，请登录");
        }
        return token;
    }

}
