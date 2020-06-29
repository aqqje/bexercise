package com.aqqje.demo.dm06;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskService {

    @Scheduled(fixedRate = 3000)
    public void task(){
        System.out.println("current-time6:" + new Date());
    }
}
