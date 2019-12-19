package com.suixingpay.service;

import com.suixingpay.mapper.ButlerSubordinatesMapper;
import com.suixingpay.mapper.ButlerUserMapper;
import com.suixingpay.pojo.ButlerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName ButlerSubordinatesServiceImpl
 *
 * @Description 根据用户id查询所有父级信息
 * @Author luyun
 * @Date 2019/12/18 17:56
 * @Version 1.0
 **/
@Service
public class ButlerSubordinatesServiceImpl implements ButlerSubordinatesServcie{

    @Autowired
    ButlerSubordinatesMapper butlerSubordinatesMapper;

    @Autowired
    ButlerUserMapper butlerUserMapper;
    /**
     * 功能描述: <根据用户id查询父类信息>
     * 〈〉
     * @Param: [id]
     * @Return: com.suixingpay.pojo.ButlerSubordinates
     * @Author: luyun
     * @Date: 2019/12/19 13:34
     */
    public List<ButlerUser> selectParentInfoBySubId(Integer id) {
        try {
           Integer userId = butlerSubordinatesMapper.selectUserIdBySubId(id);

            return butlerSubordinatesMapper.selectParentInfoBySubId(userId);
        }catch (Exception e){
            throw  new RuntimeException("错误");
        }
    }

    /**
     * 功能描述: <根据id查询直接父类id,然后查询父类id信息>
     * 〈〉
     * @Param: [id]
     * @Return: com.suixingpay.pojo.ButlerSubordinates
     * @Author: luyun
     * @Date: 2019/12/19 13:38
     */
    public ButlerUser selectParentInfoByid(int id){
        int ids=butlerSubordinatesMapper.selectLeaderByid(id);
        return butlerSubordinatesMapper.selectParentInfoByid(ids);
    }

}
