package com.suixingpay.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: http 相关的工具
 * <p>
 * Created by Jalr4ever on 2019/12/20.
 */

@Component
public class HttpUtil {

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 根据 token 的名字获取 token 值
     *
     * @param tokenName token 名字
     * @return token 值
     */
    public String getToken(String tokenName) {
        String token = httpServletRequest.getHeader(tokenName);
        if (token == null || "".equals(token)) {
            return null;
        } else {
            return token;
        }
    }

}