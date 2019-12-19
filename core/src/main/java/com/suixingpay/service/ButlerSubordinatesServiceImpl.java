package com.suixingpay.service;

import com.suixingpay.mapper.ButlerSubordinatesMapper;
import com.suixingpay.pojo.ButlerSubordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName ButlerSubordinatesServiceImpl
 *
 * @Description 根据用户id查询所有父级信息
 * @Author luyun
 * @Date 2019/12/18 17:56
 * @Version 1.0
 **/
@Service
public class ButlerSubordinatesServiceImpl {

    @Autowired
    ButlerSubordinatesMapper butlerSubordinatesMapper;

    public Integer selectUserIdBySubId(int id){

        return butlerSubordinatesMapper.selectUserIdBySubId(id);
    }

    public ButlerSubordinates selectParentInfoByid(Integer id){
        return butlerSubordinatesMapper.selectParentInfoByid(id);
    }

}
