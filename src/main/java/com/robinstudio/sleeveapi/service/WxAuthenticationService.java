package com.robinstudio.sleeveapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinstudio.sleeveapi.exception.http.ParameterException;
import com.robinstudio.sleeveapi.model.UserDO;
import com.robinstudio.sleeveapi.repository.UserRepository;
import com.robinstudio.sleeveapi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class WxAuthenticationService {
    /**
     * 1. 微信验证 返回code码，通过code码，访问code2session接口返回当前小程序的用户 openid
     * 2. openid 存入自己数据库中的 user 表 映射 uid，相当于注册，再次访问调用查询
     * 3. uid写入JWT中，返回到小程序
     */

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Value("${wx.code2session}")
    private String code2sessionUrl;
    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    private String appsecret;

    public String code2Session(String code) {
        String url = MessageFormat.format(this.code2sessionUrl, this.appid, this.appsecret, code);
        // 后端发起http请求
        RestTemplate rest = new RestTemplate();
        String sessionText = rest.getForObject(url, String.class);
        Map<String, Object> session = new HashMap<>();
        try {
            session = mapper.readValue(sessionText, Map.class); // 返回结果 { session_key: "", openid: ""}
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return this.registerUser(session);
    }

    private String registerUser(Map<String, Object> session) {
        String openid = (String) session.get("openid");
        if (openid == null) {
            throw new ParameterException(20004);
        }
        UserDO user = this.userRepository.findByOpenid(openid);
        // 查询 如果没有用户，先创建，在返回token，如果用户已存在，返回token
        if (user == null) {
            UserDO newUser = UserDO.builder().openid(openid).build();
            userRepository.save(newUser);
            // 返回令牌， uid，scope(group/level)
            return JwtToken.makeToken(newUser.getId());
        }
        return JwtToken.makeToken(user.getId());
    }
}
