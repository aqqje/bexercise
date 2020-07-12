package com.aqqje.springclouduserservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author AqqJe
 * @Date 2020/7/7
 * @Version 1.0
 **/
@RestController
public class OpenFeignController {

    @Autowired
    private OrderServiceFeignClient orderServiceFeignClient;

    @GetMapping("test")
    public String test(){
        return orderServiceFeignClient.getAllOrder();
    }
}
