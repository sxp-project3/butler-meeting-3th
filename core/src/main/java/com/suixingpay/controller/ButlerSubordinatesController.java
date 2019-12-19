package com.suixingpay.controller;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.pojo.ButlerSubordinates;
import com.suixingpay.response.Response;
import com.suixingpay.service.ButlerSubordinatesServcie;
import com.suixingpay.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName ButlerSubordinatesController
 *
 * @Description TODO
 * @Author luyun
 * @Date 2019/12/19 10:08
 * @Version 1.0
 **/

public class ButlerSubordinatesController {


    @Autowired
     private  ButlerSubordinatesServcie butlerSubordinatesServcie;


    @RequestMapping(value = "/selectParentInfo",method = RequestMethod.POST)
    public Response selectParentInfoByid(@RequestBody Integer id){
        if (Utils.isBlank(String.valueOf(id))){
            return Response.getInstance(CodeEnum.FAIL,"id不能为空");
        }
        ButlerSubordinates butlerSubordinates=butlerSubordinatesServcie.selectParentInfoByid(id);
        Map<String,ButlerSubordinates> map=new HashMap<>();
        map.put("list",butlerSubordinates);
        return Response.getInstance(CodeEnum.SUCCESS,map);

    }


}
