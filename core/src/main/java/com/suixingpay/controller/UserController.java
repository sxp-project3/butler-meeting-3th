package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.response.Response;
import com.suixingpay.service.UserService;
import com.suixingpay.vo.ButlerUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户控制器
 * <p>
 * Created by Jalr4ever on 2019/12/18.
 */

@RestController
@RequestMapping("/user")
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
}
