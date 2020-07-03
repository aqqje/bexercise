package com.aqqje.starter;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author AqqJe
 * @Date 2020/7/1
 * @Version 1.0
 **/
@ConditionalOnClass(Redisson.class)
@EnableConfigurationProperties(RedissionProperties.class)
@Configuration
public class RedissionAutoConfiguration {

    @Bean
    RedissonClient redissonClient(RedissionProperties redissionProperties){
        // 配置实例
        Config config = new Config();
        // redis 连接协议
        String prefix = "redis://";

        if(redissionProperties.isSsl()){
            prefix = "rediss://";
        }
//      // 设置 redisson 配置信息
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(prefix + redissionProperties.getHost() + ":" + redissionProperties.getPort())
                .setTimeout(redissionProperties.getTimeout());
        // 创建 Redisson 实例
        return Redisson.create(config);
    }
}
