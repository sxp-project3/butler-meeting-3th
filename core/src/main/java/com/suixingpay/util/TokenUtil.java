package com.suixingpay.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.suixingpay.handler.GlobalExceptionHandler;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.vo.ButlerUserVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * ClassName TokenUtil
 *
 * @Description TODO
 * @Author luyun
 * @Date 2019/12/18 10:55
 * @Version 1.0
 **/
@Slf4j
public class TokenUtil {
    //token密钥
    public static  final String SECRET="JKKLJOoasdlfj";

    public static final String TOKEN_NAME = "auth_token";

    public static final int UNREACHABLE_USER_ID = -404;

    //token 过期时间
    public  static  final  int calendarFiled= Calendar.DATE;
    public  static  final  int calendarInterval=10;

    /**
     * 功能描述: <JWT 生成token>
     * 〈〉
     * @Param: [user]
     * @Return: java.lang.String
     * @Author: luyun
     * @Date: 2019/12/18 11:50
     */
    public static  String creatToken(ButlerUser user) throws  RuntimeException {
        String msg="user为空";
        try {
            int id=user.getId();
            String  name=user.getName();
            String  levelNum=user.getLevelNum();
            String telephone=user.getTelephone();
            String account=user.getAccount();
            String password=user.getPassword();
            String rootUserId=user.getRootUserId();
            String leaderId=user.getLeaderId();
            String referralCode=user.getReferralCode();
            String province=user.getProvince();
            String city=user.getCity();
            String role=user.getRole();
            Date sReateTime=user.getCreateTime();
            Date sUpdateTime=user.getUpdateTime();
            String isDelete=user.getIsDelete();
            String createTime=String.valueOf(sReateTime);
            String updateTime=String.valueOf(sUpdateTime);
            //私钥加密
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            //header.Map
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            //build token
            return JWT.create().withHeader(header)
                    .withClaim("id", id)
                    .withClaim("name", name)
                    .withClaim("levelNum", levelNum)
                    .withClaim("createTime",createTime)
                    .withClaim("telephone",telephone)
                    .withClaim("account",account)
                    .withClaim("password",password)
                    .withClaim("rootUserId",rootUserId)
                    .withClaim("leaderId",leaderId)
                    .withClaim("referralCode",referralCode)
                    .withClaim("province",province)
                    .withClaim("city",city)
                    .withClaim("role",role)
                    .withClaim("updateTime",updateTime)
                    .withClaim("isDelete",isDelete)
                    .sign(algorithm);
        } catch (Exception e){
            return msg;
        }

    }
    /**
     * 功能描述: <解密token>
     * 〈〉
     * @Param: [token]
     * @Return: java.util.Map<java.lang.String,com.auth0.jwt.interfaces.Claim>
     * @Author: luyun
     * @Date: 2019/12/18 11:54
     */
    public static ButlerUserVO verifyToken(String token) {
        DecodedJWT jwt=null;
        try {
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(SECRET)).build();
             jwt=jwtVerifier.verify(token);
             jwt.getClaims();
        }catch (JWTDecodeException e){
            log.error("Token 值错误！");
            return null;
        }
        int id=jwt.getClaims().get("id").asInt();
        String levelNum=jwt.getClaims().get("levelNum").asString();
        String name=jwt.getClaims().get("name").asString();
        String telephone=jwt.getClaims().get("telephone").asString();
        String account=jwt.getClaims().get("account").asString();
        String password=jwt.getClaims().get("password").asString();
        String rootUserId=jwt.getClaims().get("rootUserId").asString();
        String leaderId=jwt.getClaims().get("leaderId").asString();
        String referralCode=jwt.getClaims().get("referralCode").asString();
        String province=jwt.getClaims().get("province").asString();
        String city=jwt.getClaims().get("city").asString();
        String role=jwt.getClaims().get("role").asString();
        Date updateTime=jwt.getClaims().get("updateTime").asDate();
        String isDelete=jwt.getClaims().get("isDelete").asString();
        Date createTime=jwt.getClaims().get("createTime").asDate();
        ButlerUserVO butlerUserVO=new ButlerUserVO();
        butlerUserVO.setId(id);
        butlerUserVO.setAccount(account);
        butlerUserVO.setCreateTime(createTime);
        butlerUserVO.setLevelNum(levelNum);
        butlerUserVO.setName(name);
        butlerUserVO.setCity(city);
        butlerUserVO.setTelephone(telephone);
        butlerUserVO.setPassword(password);
        butlerUserVO.setReferralCode(referralCode);
        butlerUserVO.setRootUserId(rootUserId);
        butlerUserVO.setIsDelete(isDelete);
        butlerUserVO.setLeaderId(leaderId);
        butlerUserVO.setUpdateTime(updateTime);
        butlerUserVO.setProvince(province);
        butlerUserVO.setRole(role);
        return butlerUserVO;
    }



}


