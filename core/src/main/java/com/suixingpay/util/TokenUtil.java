package com.suixingpay.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.handler.GlobalExceptionHandler;
import com.suixingpay.pojo.ButlerUser;
import com.suixingpay.response.Response;
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

public class TokenUtil {
    //token密钥
    public static  final String SECRET="JKKLJOoasdlfj";

    //token 过期时间
    public  static  final  int calendarFiled= Calendar.DATE;
    public  static  final  int calendarInterval=10;

    @Autowired
    public static HttpServletRequest httpServletRequest;

    @Autowired
    private static GlobalExceptionHandler globalExceptionHandler;
    /**
     * 功能描述: <JWT 生成token>
     * 〈〉
     * @Param: [userId, userName, userLevel]
     * @Return: java.lang.String
     * @Author: luyun
     * @Date: 2019/12/18 11:50
     */
    public static  String creatToken(ButlerUser user) throws  RuntimeException {
        String msg="user为空";
        try {

            int id=user.getId();
            String  name=user.getName();
            String  userLevel=user.getLevelNum();
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
                    .withClaim("userLevel", userLevel)
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
    public static Map<String, Object> verifyToken(String token){
        DecodedJWT jwt=null;
        try {
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(SECRET)).build();
             jwt=jwtVerifier.verify(token);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("token值错误");
        }
        Map<String, Object> map = new HashMap<>();
        String id=jwt.getClaims().get("id").asString();
        String userLevel=jwt.getClaims().get("userLevel").asString();
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
        map.put("id",id);
        map.put("userLevel",userLevel);
        map.put("name",name);
        map.put("telephone",telephone);
        map.put("account",account);
        map.put("password",password);
        map.put("rootUserId",rootUserId);
        map.put("referralCode",referralCode);
        map.put("province",province);
        map.put("city",city);
        map.put("leaderId",leaderId);
        map.put("updateTime",JacksonUtil.dateToString(updateTime));
        map.put("isDelete",isDelete);
        map.put("role",role);
        map.put("createTime",JacksonUtil.dateToString(createTime));
        return map;
    }



}


