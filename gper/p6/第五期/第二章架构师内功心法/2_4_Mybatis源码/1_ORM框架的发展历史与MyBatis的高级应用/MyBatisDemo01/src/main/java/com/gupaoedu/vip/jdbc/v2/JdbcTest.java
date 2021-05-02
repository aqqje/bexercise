package com.gupaoedu.vip.jdbc.v2;


import com.gupaoedu.vip.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 对JDBC原始操作优化
 *    解决问题
 *       1.重复代码
 *       2.资源管理
 */
public class JdbcTest {

    public static void main(String[] args) {
        new JdbcTest().queryUser();
       //  new JdbcTest().addUser();
    }

    /**
     *
     * 通过JDBC查询用户信息
     */
    public void queryUser(){
        Connection conn = null;
        Statement stmt = null;
        User user = new User();
        ResultSet rs = null;
        try {
            // 注册 JDBC 驱动
            // Class.forName("com.mysql.cj.jdbc.Driver");

            // 打开连接
            conn = DBUtils.getConnection();
            // 执行查询
            stmt = conn.createStatement();
            String sql = "SELECT id,user_name,real_name,password,age,d_id from t_user where id = 1";
            rs = stmt.executeQuery(sql);

            // 获取结果集
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String userName = rs.getString("user_name");
                String realName = rs.getString("real_name");
                String password = rs.getString("password");
                Integer did = rs.getInt("d_id");
                user.setId(id);
                user.setUserName(userName);
                user.setRealName(realName);
                user.setPassword(password);
                user.setDId(did);
                System.out.println(user);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn,stmt,rs);
        }
    }

    /**
     * 通过JDBC实现添加用户信息的操作
     */
    public void addUser(){
        Connection conn = null;
        Statement stmt = null;
        try {
            // 打开连接
            conn = DBUtils.getConnection();
            // 执行查询
            stmt = conn.createStatement();
            String sql = "INSERT INTO t_user(user_name,real_name,password,age,d_id)values('wangwu','王五','111',22,1001)";
            int i = stmt.executeUpdate(sql);
            System.out.println("影响的行数:" + i);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(conn,stmt);
        }
    }
}
