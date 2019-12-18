package com.suixingpay.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.suixingpay.pojo.ButlerUser;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 功能描述: <JWT 生成token>
     * 〈〉
     * @Param: [userId, userName, userLevel]
     * @Return: java.lang.String
     * @Author: luyun
     * @Date: 2019/12/18 11:50
     */
    public static  String creatToken(ButlerUser user) throws  RuntimeException {

        try {
            Integer userId=user.getId();
            String  userName=user.getName();
            String  userLevel=user.getLevelNum();
            String tele=user.getTelephone();
            String account=user.getAccount();
            String password=user.getPassword();
            String rootUserId=user.getRootUserId();
            String leaderId=user.getLeaderId();
            String referralCode=user.getReferralCode();
            String province=user.getProvince();
            String city=user.getCity();
            String role=user.getRole();
            Date createTime=user.getCreateTime();
            Date updateTime=user.getUpdateTime();
            String isDelete=user.getIsDelete();


            //过期时间
            Calendar nowTime = Calendar.getInstance();
            nowTime.add(calendarFiled, calendarInterval);
            Date expireTime = nowTime.getTime();
            //私钥加密
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            //header.Map
            Map<String, Object> header = new HashMap<>();
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            //build token
            return JWT.create().withHeader(header)
                    .withClaim("userId", userId)
                    .withClaim("userName", userName)
                    .withClaim("userLevel", userLevel)
                    .withClaim("createTime",createTime)
                    .withClaim("tele",tele)
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
                    .withExpiresAt(expireTime)
                    .sign(algorithm);
        } catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    /**
     * 功能描述: <解密token>
     * 〈〉
     * @Param: [token]
     * @Return: java.util.Map<java.lang.String,com.auth0.jwt.interfaces.Claim>
     * @Author: luyun
     * @Date: 2019/12/18 11:54
     */
    public static Map<String, Claim> verifyToken(String token){
        DecodedJWT jwt=null;
        try {
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt=jwtVerifier.verify(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jwt.getClaims();
    }
    /**
     * 功能描述: <根据token获取userId>
     * 〈〉
     * @Param: [token]
     * @Return: int
     * @Author: luyun
     * @Date: 2019/12/18 12:00
     */
    public static int getId(String token){
        Map<String,Claim> claimMap=verifyToken(token);
        Claim userId = claimMap.get("user_id");
        if (Utils.isBlank(String.valueOf(userId))){
            new RuntimeException("token异常 重新登录");
        }
        return Integer.valueOf(userId.asString());

    }


}
