package com.suixingpay.mapper;

import com.suixingpay.pojo.Sign;
import com.suixingpay.response.Response;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author hyx
 */

@Mapper
@Component
public interface SignMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Sign record);
//
//    int insertSelective(Sign record);
//
//    Sign selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Sign record);
//
//    int updateByPrimaryKey(Sign record);

      int insertByIn(Sign sign);

      int insertByUp(Sign sign);

      int updateByIn(Sign sign);

      List<Integer> selectByMeetId(Integer integer);

      List<Sign> selectAll();


}