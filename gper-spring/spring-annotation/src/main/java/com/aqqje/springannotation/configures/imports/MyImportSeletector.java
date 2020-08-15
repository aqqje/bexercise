package com.aqqje.springannotation.configures.imports;

import com.aqqje.springannotation.project.entity.Company;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
public class MyImportSeletector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.aqqje.springannotation.project.entity.Company", "com.aqqje.springannotation.project.entity.Member"};
    }
}
