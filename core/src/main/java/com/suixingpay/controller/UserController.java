package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.response.Response;
import com.suixingpay.service.UserService;
import com.suixingpay.util.HttpUtil;
import com.suixingpay.util.TokenUtil;
import com.suixingpay.vo.ButlerUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户控制器
 * <p>
 * Created by Jalr4ever on 2019/12/18.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpUtil httpUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response userLogIn(@RequestBody @Validated ButlerUser butlerUser) {

        ButlerUserVO butlerUserVO = userService.userLogIn(butlerUser);

        // 登录的判断通过是否能取到服务层相应的 token 来判断
        if (butlerUserVO.getToken() == null) {
            return Response.getInstance(CodeEnum.FAIL, "登录失败，请检查用户或密码是否正确！");
        } else {
            return Response.getInstance(CodeEnum.SUCCESS, butlerUserVO);
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Response indexInfo() {

        // 用户登录请求的主页信息，如果已经登录，则不需要请求 login 接口
        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);

        if (token == null) {
            return Response.getInstance(CodeEnum.FAIL, "传入的 token 为空！");
        } else {
            String parseResult = userService.isUserLogin(token);
            if (parseResult == null) {
                // 返回错误状态，查不到响应的登录信息
                return Response.getInstance(CodeEnum.FAIL, null);
            } else {
                // 返回成功状态，查到了用户的 token 创建时间
                return Response.getInstance(CodeEnum.SUCCESS, parseResult);
            }
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Response logOut() {

        // 取得当前已经登录的用户 token
        String token = httpUtil.getToken(TokenUtil.TOKEN_NAME);
        if (token == null) {
            return Response.getInstance(CodeEnum.FAIL, "当前 token 为空");
        } else {
            ButlerUserVO butlerUserVO = userService.userLogOut(token);

            // 校验 token ，返回 null 或者 user.id 不可达，说明有错
            CodeEnum returnCodeEnum = CodeEnum.FAIL;
            String returnMsg = "null";

            if (butlerUserVO == null) {
                returnMsg = "校验 token 错误，请重新传递！";
            } else if (butlerUserVO.getId() == TokenUtil.UNREACHABLE_USER_ID) {
                returnMsg = "退出失败！当前用户已退出！请勿重复退出！";
            } else {
                returnMsg = "用户： " + butlerUserVO.getAccount() + " 退出成功!";
                returnCodeEnum = CodeEnum.SUCCESS;
            }
            return Response.getInstance(returnCodeEnum, returnMsg);
        }
    }

}
