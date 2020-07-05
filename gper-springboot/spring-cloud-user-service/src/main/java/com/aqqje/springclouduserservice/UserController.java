package com.aqqje.springclouduserservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author AqqJe
 * @Date 2020/7/5
 * @Version 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    /**
     * 使用 restTemplate http 方式调用远程服务
     * @param id
     * @return
     */
    /*@GetMapping("/user/{id}")
    public String findById(@PathVariable("id") int id)
    {
        return restTemplate.getForObject("http://localhost:8081/orders", String.class);
    }*/


    /**
     * 使用 ribbon 负载均衡调用服务
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public String findById(@PathVariable("id") int id)
    {
        ServiceInstance serviceInstance = loadBalancerClient.choose("spring-cloud-order-service");
        String url = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort() + "/orders");
        return restTemplate.getForObject(url, String.class);
    }


}
