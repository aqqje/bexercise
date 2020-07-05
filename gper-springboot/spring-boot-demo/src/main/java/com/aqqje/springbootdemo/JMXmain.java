package com.aqqje.springbootdemo;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * @Author AqqJe
 * @Date 2020/7/3
 * @Version 1.0
 **/
public class JMXmain {

    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.aqqje.springbootdemo:type=SystemInfo");
        SystemInfo systemInfo = new SystemInfo();
        mBeanServer.registerMBean(systemInfo,objectName);
        System.in.read();
    }
}
