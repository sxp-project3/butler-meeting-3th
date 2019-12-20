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
    public  static boolean isBlank(String str) {
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


}
