package com.demo.pattern.proxy.dbroute;

import java.util.Date;

public class Order {
    public long getCreateTime(){
        return System.currentTimeMillis();
    }
}
