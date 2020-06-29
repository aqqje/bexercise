package com.aqqje.demo.dm04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.aqqje.demo.dm03.SpringConfiguration;
@Configuration
@Import(SpringConfiguration.class)
public class ImportSelecterConfiguration {

    @Bean
    public ImportSelecterService importSelecterService(){
        return new ImportSelecterService();
    }
}
