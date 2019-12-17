package com.suixingpay.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 测试启动用控制器
 * <p>
 * Created by Jalr4ever on 2019/12/17.
 */
@Controller
public class HelloController {

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hi, it's building success!";
    }
}
