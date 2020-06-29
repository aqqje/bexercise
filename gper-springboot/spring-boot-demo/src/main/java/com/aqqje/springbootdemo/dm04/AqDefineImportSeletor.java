package com.aqqje.springbootdemo.dm04;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import com.aqqje.springbootdemo.dm03.MybatisConfiguration;
import com.aqqje.springbootdemo.dm02.RedisConfiguration;


/**
 * 使用importSelect 将其他jar/项目的configuration, 加入到IOC容器
 */
public class AqDefineImportSeletor implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 动态导入bean, 告诉了Spring, 两个配置类在哪里
        // TODO 在这里去加载所有的配置类就行了？
        // 通过某种机制去完成指定路径的配置类的扫描就行了？
        return new String[]{RedisConfiguration.class.getName(), MybatisConfiguration.class.getName()};
    }
}
