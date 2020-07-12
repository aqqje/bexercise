package com.aqqje.springclouduserservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author AqqJe
 * @Date 2020/7/7
 * @Version 1.0
 **/
@FeignClient("spring-cloud-order-service")
public interface OrderServiceFeignClient {

    @GetMapping("/orders")
    String getAllOrder();
}
