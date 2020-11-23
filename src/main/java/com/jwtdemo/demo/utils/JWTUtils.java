package com.jwtdemo.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    //JWT钥密
    private static String SECERT = "!w#6^uW&jd9";

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    /**
     * jwt生成token
     * @param map
     * @return
     */
    public static String getToken(Map<String, String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)-> {
            builder.withClaim(k,v);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7); //默认7天过期
        builder.withExpiresAt(instance.getTime());
        String token = builder.sign(Algorithm.HMAC256(SECERT));
        return token;
    }

    /**
     * 解析获取token信息
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token){
       return JWT.require(Algorithm.HMAC256(SECERT)).build().verify(token);
    }

    // 测试
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username","zhangsa");
        map.put("department","yanfa");
        String token = getToken(map);
        logger.info("token,{}",token);
        DecodedJWT verifyToken = verifyToken(token);
        String username = verifyToken.getClaim("username").asString();
        String department = verifyToken.getClaim("department").asString();
        System.out.println(username+"----"+department);
    }
}
