package com.suixingpay.mapper;

import com.suixingpay.pojo.ButlerSubordinates;
import com.suixingpay.pojo.ButlerUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 用户下属表的 mapper
 * <p>
 * Created by jalr on 2019/12/18.
 */
@Mapper
public interface ButlerSubordinatesMapper {
    /**
     * 功能描述: <根据用户id查询所有父级信息>
     * 〈〉
     * @Param: [id]
     * @Return: java.lang.Integer
     * @Author: luyun
     * @Date: 2019/12/18 17:52
     */
    Integer selectUserIdBySubId(Integer id);

    ButlerSubordinates selectParentInfoByid(Integer id);

}
