package com.aqqje.springannotation.configures.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
public class LinuxConditional implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 从IOC容器中拿到已经实例化的对象
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        Environment environment = conditionContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);
        if(osName.toLowerCase().contains("liunx")){
            return true;
        }
        return false;
    }
}
