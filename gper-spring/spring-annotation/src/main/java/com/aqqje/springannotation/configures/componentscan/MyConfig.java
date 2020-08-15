package com.aqqje.springannotation.configures.componentscan;

import com.aqqje.springannotation.project.Controller.MyController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 *
 * @ComponentScan 默认会扫描该类所在下所有的配置类，相当于之前 <context:component-scan>
 **/
@Configuration
@ComponentScan(value = "com.aqqje.springannotation.project",
//          includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class})},
//         includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {MyController.class})},
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, value = MyTypeFilter.class)},
        useDefaultFilters = true)
public class MyConfig {
}
