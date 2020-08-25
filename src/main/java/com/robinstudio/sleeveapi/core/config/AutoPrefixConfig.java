package com.robinstudio.sleeveapi.core.config;

import com.robinstudio.sleeveapi.core.hack.AutoPrefixUrlMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

// 通过接口方式实现发现机制，另一种方式是加上特定注解，比如 @ControllerAdvice
// @Component
public class AutoPrefixConfig implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new AutoPrefixUrlMapping();
    }
}
