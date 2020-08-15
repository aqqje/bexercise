package com.aqqje.springannotation.configures.componentscan;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
public class MyTypeFilter implements TypeFilter {
    /**
     *
     * @param metadataReader 获取当前操作信息
     * @param metadataReaderFactory 获取上下文中所有信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前扫描到的类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前扫描到的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前扫描到的类的资源
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        System.out.println("=========" + className + "=========");
        if(className.contains("er")) {
            return true;
        }
        return false;
    }
}
