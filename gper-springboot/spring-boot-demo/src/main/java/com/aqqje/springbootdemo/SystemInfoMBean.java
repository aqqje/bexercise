package com.aqqje.springbootdemo;

/**
 * @Author AqqJe
 * @Date 2020/7/3
 * @Version 1.0
 **/
public interface SystemInfoMBean {

    int getCpuCore();

    long totalMemory();

    void shutdown();
}
