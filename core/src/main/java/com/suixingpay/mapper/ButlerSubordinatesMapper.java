package com.suixingpay.mapper;

import com.suixingpay.pojo.ButlerUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 功能描述: <根据用户id查询所有父级id>
     * 〈〉
     * @Param: [id]
     * @Return: java.lang.Integer
     * @Author: luyun
     * @Date: 2019/12/18 17:52
     */
    List<Integer> selectUserIdBySubId(@Param("id") Integer id);
    /**
     * 功能描述: <根据id查询直接父类信息>
     * 〈〉
     * @Param: [id]
     * @Return: com.suixingpay.pojo.ButlerSubordinates
     * @Author: luyun
     * @Date: 2019/12/19 11:06
     */
    ButlerUser selectParentInfoByid(@Param("id") Integer id);
    /**
     * 功能描述: <根据id获取直接父类id>
     * 〈〉
     * @Param: [id]
     * @Return: java.lang.Integer
     * @Author: luyun
     * @Date: 2019/12/19 11:08
     */
    Integer selectLeaderByid(@Param("id") Integer id);
    /**
     * 功能描述: <根据用户id查询父类信息>
     * 〈〉
     * @Param: [id]
     * @Return: java.util.List<com.suixingpay.pojo.ButlerSubordinates>
     * @Author: luyun
     * @Date: 2019/12/19 13:33
     */
    List<ButlerUser> selectParentInfoBySubId(@Param("userId") List<Integer> userId);

    /**
     * 功能描述: <根据id查询用户信息>
     * 〈〉
     * @Param: [id]
     * @Return: com.suixingpay.pojo.ButlerUser
     * @Author: luyun
     * @Date: 2019/12/19 21:10
     */
    ButlerUser selectByid(@Param("id") Integer id);

}
