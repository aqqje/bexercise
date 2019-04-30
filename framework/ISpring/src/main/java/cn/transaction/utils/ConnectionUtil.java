package cn.transaction.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接工具类
 * 使其只有一个连接
 */
@Component("connUtil")
public class ConnectionUtil {

    private ThreadLocal<Connection> tl = new ThreadLocal<>();
    private Connection conn = null;
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*把连接与线程绑定*/
    public Connection getThreadConnection(){
        try {
            conn = tl.get();
            if(conn == null){
                conn = dataSource.getConnection();
                tl.set(conn);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("连接异常!");
        }
        return conn;
    }

    /*把连接与线程解绑*/
    public void removeConnection(){
        tl.remove();
    }

}
