package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.ButlerSubordinates;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.response.Response;
import com.suixingpay.service.ButlerSubordinatesServcie;
import com.suixingpay.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName ButlerSubordinatesController
 *
 * @Description 接口
 * @Author luyun
 * @Date 2019/12/19 10:08
 * @Version 1.0
 **/
@Controller
public class ButlerSubordinatesController {


    @Autowired
     private  ButlerSubordinatesServcie butlerSubordinatesServcie;


    @RequestMapping(value = "/selectParentInfo",method = RequestMethod.POST)
    @ResponseBody
    public Response selectParentInfoByid(@RequestBody ButlerSubordinates butlerSubordinates){
        Integer id=butlerSubordinates.getId();
        if (Utils.isBlank(String.valueOf(id))){
            return Response.getInstance(CodeEnum.FAIL,"id不能为空");
        }
        //根据id查询直接父类信息
        ButlerUser butlerUser=butlerSubordinatesServcie.selectParentInfoByid(id);
        Map<String,ButlerUser> map=new HashMap<>();
        map.put("list",butlerUser);
        return Response.getInstance(CodeEnum.SUCCESS,map);
    }

    @RequestMapping(value = "/select",method = RequestMethod.POST)
    @ResponseBody
    public Response selectParentInfoBySubId(@RequestBody ButlerUser butlerUser){
        Integer id=butlerUser.getId();
        if (Utils.isBlank(String.valueOf(id))){
            return Response.getInstance(CodeEnum.FAIL,"id不能为空");
        }
        //根据id查询直接父类信息
        Map<String, List<ButlerUser>> map=new HashMap<>();
        List<ButlerUser> list=butlerSubordinatesServcie.selectParentInfoBySubId(id);
        map.put("list",list);
        return Response.getInstance(CodeEnum.SUCCESS,map);
    }


}
