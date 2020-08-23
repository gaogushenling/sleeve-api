package com.robinstudio.sleeveapi.api.v1;

import com.robinstudio.sleeveapi.dto.TokenDTO;
import com.robinstudio.sleeveapi.dto.TokenGetDTO;
import com.robinstudio.sleeveapi.service.WxAuthenticationService;
import com.robinstudio.sleeveapi.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/v1/token")
@RestController
public class TokenController {
    @Autowired
    private WxAuthenticationService wxAuthenticationService;

    // 获取 token
    @PostMapping("")
    public Map<String, String> getToken(@RequestBody @Validated TokenGetDTO userData) {
        Map<String, String> map = new HashMap<>();
        String token = null;
        switch (userData.getType()) {
            case USER_WX:
                token = wxAuthenticationService.code2Session(userData.getAccount());
                break;
            case USER_EMAIL:
                break;
            default:
                // throw new NotFoundException(10003)
        }
        map.put("token", token);
        return map;
    }

    // 校验 token
    @PostMapping("/verify")
    public Map<String, Boolean> verifyToken(@RequestBody TokenDTO token) {
        Map<String, Boolean> map = new HashMap<>();
        Boolean valid = JwtToken.verifyToken(token.getToken());
        map.put("is_valid", valid);
        return map;
    }
}
