package com.aqqje.springbootdemo.dm01;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * spring boot 根据 Condition 判断是否进行装载
 */
public class AqCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // TODO semething
        return true;
    }
}
