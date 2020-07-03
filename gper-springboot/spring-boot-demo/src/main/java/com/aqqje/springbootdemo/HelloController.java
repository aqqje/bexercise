package com.aqqje.springbootdemo;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Autowired
    private RedissonClient redissonClient;

    /*@GetMapping("/test")
    public String hello(){
        return "hello!";
    }*/

    @GetMapping("/say")
    public String say(){
        RBucket<String> bucket = redissonClient.getBucket("name");
        if(bucket.get() == null){
            bucket.set("aqqje.com");
        }
        return bucket.get();
    }
}
