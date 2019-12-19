package com.suixingpay.service;

import com.suixingpay.mapper.SignMapper;
import com.suixingpay.pojo.Sign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignServiceImpl implements SignService{

    private static final Logger LOGGER = LoggerFactory.getLogger(SignService.class);

    @Autowired
    private SignMapper signMapper;


    //会议报名的逻辑实现
    @Override
    public int signUpActive(Sign sign) {


//        Integer userId = sign.getUserId();
//        Integer meetingId = sign.getMeetingId();
//
//        //用户参数判空
//        if (userId == null){
//            LOGGER.info("报名失败，不存在此用户");
//            return Response.getInstance(CodeEnum.FAIL,"请选择正确的用户");
//        }
//
//        //会议参数判空
//        if (meetingId == null){
//            LOGGER.info("报名失败，不存在此会议");
//            return Response.getInstance(CodeEnum.FAIL,"请选择正确的会议");
//        }
//
//        sign.setUserId(userId);
//        sign.setMeetingId(meetingId);
//        sign.setSignupTime(new Date());
//        sign.setIsSignup(1);
//        signMapper.signUpActive(sign);
//        LOGGER.info("报名成功");
        return signMapper.signUpActive(sign);
    }


    //会议签到的逻辑实现
    @Override
    public List<Integer> selectIdByMeeting(Sign sign) {
        //查询出当前会议下所有用户用作签到判断
        List<Sign> list = signMapper.selectIdByMeeting(sign);
        //定义一个list装每一查询出的对象的userid
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
           Integer id = list.get(i).getUserId();
            list1.add(id);
        }
        return list1;
    }

    @Override
    public int updateSignIn(Sign sign) {
        return signMapper.updateSignIn(sign);
    }

    @Override
    public int insertSignIn(Sign sign) {
        return signMapper.insertSignIn(sign);
    }

    //通过meetingId查询签到人数
    @Override
    public int selectCountSignIn(Integer integer) {
        return signMapper.selectCountSignIn(integer);
    }

    //通过meetingId查询报名人数
    @Override
    public int selectCountSignUp(Integer integer) {
        return signMapper.selectCountSignUp(integer);
    }

    //通过meetingId和userId查询除主键和userId以外的数据
    @Override
    public Sign selectWithOutIdAndUserId(Sign sign) {
        return signMapper.selectWithOutIdAndUserId(sign);
    }
}
