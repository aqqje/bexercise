package com.aqqje.springannotation.configures.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
public class WindowsConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        String osName = context.getEnvironment().getProperty("os.name");
        System.out.println(osName);
        if(osName.toLowerCase().contains("windows")){
            return true;
        }
        return false;
    }
}
