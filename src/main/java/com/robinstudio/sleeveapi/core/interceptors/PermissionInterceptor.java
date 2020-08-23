package com.robinstudio.sleeveapi.core.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.robinstudio.sleeveapi.exception.http.ForbiddenException;
import com.robinstudio.sleeveapi.exception.http.UnauthenticatedException;
import com.robinstudio.sleeveapi.util.JwtToken;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * 拦截器逻辑
 * 1. 获取请求中的token
 * 2. 验证token
 * 3. 比较token 中的scope 与接口 @ScopeLevel 值的大小，判断是否有权限访问
 */

public class PermissionInterceptor extends HandlerInterceptorAdapter {

    public PermissionInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        // 访问接口方法没有scopeLevel注解，则认为是公开接口
        if (!scopeLevel.isPresent()) {
            return true;
        }

        // 获取前端返回的token
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isEmpty(bearerToken)) {
            throw new UnauthenticatedException(10004);
        }
        if (!bearerToken.startsWith("Bearer")) {
            throw new UnauthenticatedException(10004);
        }
        String[] tokens = bearerToken.split(" ");
        if(tokens.length != 2){
            throw new UnauthenticatedException(10004);
        }
        String token = tokens[1];
        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        Map<String, Claim> map = optionalMap.orElseThrow(() ->
                new UnauthenticatedException(10004)
        );
        boolean valid = this.hasPermission(scopeLevel.get(), map);
        return valid;
    }

    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map) {
        Integer level = scopeLevel.value();
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new ForbiddenException(10005);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private Optional<ScopeLevel> getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if(scopeLevel == null){
                return Optional.empty();
            }
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }
}
