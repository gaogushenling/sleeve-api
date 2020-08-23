package com.robinstudio.sleeveapi.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtToken {
    private static String jwtKey;
    private static Integer expiredAt;
    private static Integer defaultScope = 8; // 随意的默认值

    @Value("${sleeve.security.jwt-key}")
    public void setJwtKey(String jwtKey) {
        JwtToken.jwtKey = jwtKey;
    }

    @Value("${sleeve.security.token-expired-at}")
    public void setExpiredAt(Integer expiredAt) {
        JwtToken.expiredAt = expiredAt;
    }

    public static String makeToken(Integer uid, Integer scope) {
        return JwtToken.getToken(uid, scope);
    }

    public static String makeToken(Integer uid) {
        return JwtToken.makeToken(uid, defaultScope);
    }

    // 生成令牌
    private static String getToken(Integer uid, Integer scope) {
        // jwt.io
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        Map<String, Date> map = JwtToken.calculateExpiredIssues();

        return JWT.create()
                .withClaim("uid", uid) // 附加声明参数
                .withClaim("scope", scope) // 权限等级
                .withExpiresAt(map.get("expiredTime")) // 过期日期
                .withIssuedAt(map.get("now"))  // 签发日期
                .sign(algorithm);
    }

    // 计算过期日期
    private static Map<String, Date> calculateExpiredIssues() {
        Map<String, Date> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND, JwtToken.expiredAt);
        map.put("now", now);
        map.put("expiredTime", calendar.getTime());
        return map;
    }

    // 返回token中携带的声明参数
    public static Optional<Map<String, Claim>> getClaims(String token) {
        DecodedJWT decodedJWT;
        Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            decodedJWT = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            // todo 异常情况：1. 无效令牌  2. 令牌过期 具体实现
            // return null;
            return Optional.empty();
        }
        // return decodedJWT.getClaims();
        return Optional.of(decodedJWT.getClaims());
    }

    // 校验令牌
    public static Boolean verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(JwtToken.jwtKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        }catch (JWTVerificationException e){
            return false;
        }
        return true;
    }
}
