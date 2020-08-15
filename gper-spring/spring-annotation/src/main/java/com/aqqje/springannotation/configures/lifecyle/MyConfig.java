package com.aqqje.springannotation.configures. lifecyle;

import com.aqqje.springannotation.project.entity.Car;
import com.aqqje.springannotation.project.entity.Train;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 *
 * @PostConstruct 用于指定初始化方法（用在方法上）
 * @PreDestory 用于指定 销毁方法（用在方法上）
 * @DependsOn 定义Bean初始化及销毁时的顺序
 **/
@Configuration
@ComponentScans({@ComponentScan("com.aqqje.springannotation.project.entity"),
        @ComponentScan("com.aqqje.springannotation.configures.lifecyle")})
public class MyConfig {

    // 周期的3种方法
    // 1， 添加initMethod 和 destroyMethod
    // 2， 实现InitlizingBean 和 DisposableBean 接口
    // 3， 使用@PostConstruct 和 @PreDestroy 注解
    // 4,  自己实现接口BeanPostProcessor

    @Lazy
    @Bean(initMethod = "addOil", destroyMethod = "close")
    public Car car(){
        return new Car();
    }

    @Bean
    public Train train(){
        return new Train();
    }
}
