package cn.xmlcustom.mybaties.utils;

import cn.xmlcustom.mybaties.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceUtil {

    public static Connection getConncetion(Configuration cfg) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getDriver());
        return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
    }
}
