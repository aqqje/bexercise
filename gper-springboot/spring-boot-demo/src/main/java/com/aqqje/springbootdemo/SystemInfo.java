package com.aqqje.springbootdemo;

/**
 * @Author AqqJe
 * @Date 2020/7/3
 * @Version 1.0
 **/
public class SystemInfo implements SystemInfoMBean {
    @Override
    public int getCpuCore() {
        return Runtime.getRuntime().availableProcessors();
    }

    @Override
    public long totalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }
}
