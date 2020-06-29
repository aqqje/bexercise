package com.aqqje.spi.mysql;

import com.aqqje.spi.DataBaseDriver;

/**
 * @Author AqqJe
 * @Date 2020/6/29
 * @Version 1.0
 **/
public class MysqlDriver implements DataBaseDriver {
    @Override
    public String buildConnect(String host) {
        return "MYSQL的驱动实现：" + host;
    }
}
