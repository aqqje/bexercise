package com.aqqje.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author AqqJe
 * @Date 2020/7/1
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "aq.redission")
public class RedissionProperties {

    // 连接地址
    private String host = "localhost";
    //　连接端口
    private Integer port = 6379;
    // 超时时间
    private Integer timeout = 1000;
    // 是否开启ssl连接
    private boolean ssl = false;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }
}
