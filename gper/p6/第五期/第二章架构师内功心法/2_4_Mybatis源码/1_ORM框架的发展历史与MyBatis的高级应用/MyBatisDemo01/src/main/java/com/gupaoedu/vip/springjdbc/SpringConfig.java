package com.gupaoedu.vip.springjdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class SpringConfig {

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("changle@1999");
        dataSource.setUrl("jdbc:mysql://124.71.29.101:3306/mybatisdb?characterEncoding=utf-8&serverTimezone=UTC");
        return  dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);
        return template;
    }
}
