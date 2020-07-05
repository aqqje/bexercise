package com.aqqje.springbootdemo;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author AqqJe
 * @Date 2020/7/3
 * @Version 1.0
 **/
@Endpoint(id = "customer")
public class CustomerHealthIndicator {

    @ReadOperation
    public Map<String, String> time(){
        Map<String, String> map = new HashMap<>();
        map.put("currenttime:" , new Date().toString());
        return map;
    }

}
