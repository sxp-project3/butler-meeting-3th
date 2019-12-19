package com.suixingpay.service;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.mapper.SignMapper;
import com.suixingpay.pojo.Sign;
import com.suixingpay.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hyx
 */

@Service
public class SignServiceImpl implements SignService{

    @Autowired
    SignMapper signMapper;


    @Override
    public Response insertByIn(Sign sign) {
        if (sign.getUserId()==null){
            return Response.getInstance(CodeEnum.FAIL,"请选择正确的用户");
        } else if(sign.getMeetingId()==null){
            return Response.getInstance(CodeEnum.FAIL,"请选择正确的会议");
        }
        return Response.getInstance(CodeEnum.SUCCESS,"签到成功");
    }

    @Override
    public int insertByUp(Sign sign) {
        return signMapper.insertByUp(sign);
    }

    @Override
    public int updateByIn(Sign sign) {
        return 0;
    }

    @Override
    public List<Integer> selectByMeetId(Integer integer) {
        return null;
    }

    @Override
    public List<Sign> selectAll() {

        return signMapper.selectAll();

    }
}
