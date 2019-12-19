package com.suixingpay.controller;

import com.suixingpay.pojo.Sign;
import com.suixingpay.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hyx
 */
@RestController
public class SignController {

    @Autowired
    SignService signService;

    @RequestMapping("insertByIn")
    public Integer insertByIn(){
        Sign sign = new Sign();
        sign.setUserId(2221);
        sign.setMeetingId(9999);
        signService.insertByIn(sign);
        return 1;
    }

    @RequestMapping("select")
    public List<Sign> select(){

        return signService.selectAll();
    }

    @RequestMapping("insertByUp")
    public Integer insertByUp(){
        Sign sign = new Sign();
        sign.setUserId(0002);
        sign.setMeetingId(9998);
        signService.insertByUp(sign);
        return 1;
    }

}
