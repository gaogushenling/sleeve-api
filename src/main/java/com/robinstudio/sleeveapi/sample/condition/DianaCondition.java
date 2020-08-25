package com.robinstudio.sleeveapi.sample.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DianaCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String name = conditionContext.getEnvironment().getProperty("hero.condition");  // 通过 conditionContext.getEnvironment() 可以获取系统内的参数
        return "diana".equalsIgnoreCase(name);
    }
}
