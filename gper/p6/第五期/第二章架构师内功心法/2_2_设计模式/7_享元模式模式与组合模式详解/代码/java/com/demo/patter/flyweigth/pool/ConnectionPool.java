package com.demo.patter.flyweigth.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
    private Vector<Connection> pool;

    private int poolSize = 100;
    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "root";
    private String driverClassName = "com.mysql.jdbc.Driver";

    public ConnectionPool(){
        pool = new Vector<Connection>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClassName);
                Connection connection = DriverManager.getConnection(url, username, password);
                pool.add(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Connection getConnection(){
        if(pool.size() > 0){
            Connection connection = pool.get(0);
            pool.remove(connection);
            return connection;

        }
        return null;
    }
    public synchronized void release(Connection connection){
        pool.add(connection);
    }
}
