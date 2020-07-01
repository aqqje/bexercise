package com.aqqje.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 *
 * 模拟 spring boot starter 组件自动装载过程
 * @Author AqqJe
 * @Date 2020/6/30
 * @Version 1.0
 **/
@ConditionalOnClass(ConditionalOnClass.class)
@Configuration
public class AqqjeConfiguration implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{AqqjeService.class.getName()};
    }
}
