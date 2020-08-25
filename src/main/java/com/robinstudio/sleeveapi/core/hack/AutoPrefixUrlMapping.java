package com.robinstudio.sleeveapi.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 自动将菜单目录转化为路由前缀
 * 对于不存在的路径没有返回结果，需要优化
 * 暂时禁用，如果需要开启 /core/config/AutoPrefixConfig 先注入IOC
 */
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {
    @Value("${project.api-package}")
    private String apiPackagePath;

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        if (mappingInfo != null) {
            String prefix = this.getPrefix(handlerType);
            return RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
        }
        return mappingInfo;
    }

    private String getPrefix(Class<?> handlerType) {
        String packageName = handlerType.getPackage().getName();
        String dotPath = packageName.replaceAll(apiPackagePath, "");
        return dotPath.replace(".", "/");
    }
}
