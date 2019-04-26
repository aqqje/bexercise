package cn.introduction.jdbc;

import cn.introduction.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {

    /*连接参数*/
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///mybaties?useUnicode=true&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection conn = null;
    private static Statement  statement = null;
    private static ResultSet resultSet = null;
    /*加载驱动*/
    static{
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);


    }

    public static List<User> findAll() throws SQLException {

        /*编写 sql*/
        String sql = "select * from user";
        /*获取连接对象*/
        conn = getConnection();
        Statement statement = conn.createStatement();
        /*获取执行对象*/
        /*执行 sql, 获取结果集*/
        ResultSet resultSet = statement.executeQuery(sql);
        List<User> list = new ArrayList<>();
        /*封装续集*/
        while(resultSet.next()){
            User u = new User();
            u.setId(resultSet.getInt("id"));
            u.setUsername(resultSet.getString("username"));
            u.setBirthday(resultSet.getTimestamp("birthday"));
            u.setSex(resultSet.getString("sex"));
            u.setAddress(resultSet.getString("address"));
            list.add(u);
        }
        /*关闭连接*/
        resultSet.close();;
        statement.close();
        conn.close();
        return list;
    }

    public static void main(String[] args) throws SQLException {
        for (User u:findAll()) {
            System.out.println(u);
        }
    }
}
