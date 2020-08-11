package com.aqqje.springannotation.configures.imports;

import com.aqqje.springannotation.project.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry 完成BeanDefinition的注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean company = registry.containsBeanDefinition("com.aqqje.springannotation.project.entity.Company");
        boolean member = registry.containsBeanDefinition("com.aqqje.springannotation.project.entity.Member");
        if(company && member){
            BeanDefinition rootBeanDefinition = new RootBeanDefinition(User.class);
            registry.registerBeanDefinition("user", rootBeanDefinition);
        }

    }
}
