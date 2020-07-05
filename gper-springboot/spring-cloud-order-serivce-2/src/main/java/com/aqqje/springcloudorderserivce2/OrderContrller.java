package com.aqqje.springcloudorderserivce2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author AqqJe
 * @Date 2020/7/5
 * @Version 1.0
 **/
@RestController
public class OrderContrller {


    @Value("${server.port}")
    private String port;

    @GetMapping("/orders")
    public String orders(){
        System.out.println("port: " + port);
        return "Return All Orders";
    }
}
