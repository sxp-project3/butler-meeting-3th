package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.response.Response;
import com.suixingpay.service.UserService;
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

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public Response indexInfo(@RequestBody Map<String, Object> param) {

        // 用户登录请求的主页信息，如果已经登录，则不需要请求 login 接口
        String token = (String) param.get("auth_token");

        if (token == null || "".equals(token)) {
            return Response.getInstance(CodeEnum.FAIL, "传入的 token 为空！");
        } else {
            String parseResult = userService.isUserLogin(token);
            return Response.getInstance(CodeEnum.SUCCESS, parseResult);
        }

    }

}
