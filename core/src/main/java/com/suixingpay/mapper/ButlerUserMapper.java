package com.suixingpay.mapper;

import com.suixingpay.pojo.ButlerUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户的数据库 mapper
 * <p>
 * Created by Jalr4ever on 2019/12/18.
 */
@Mapper
public interface ButlerUserMapper {

    /**
     * 根据用户实体相关的参数来查询指定的用户集合
     *
     * @param butlerUser 用户实体
     * @return 用户实体集合，若查不到，返回集合 0
     */
    List<ButlerUser> selectUserAnyCondition(ButlerUser butlerUser);

}
