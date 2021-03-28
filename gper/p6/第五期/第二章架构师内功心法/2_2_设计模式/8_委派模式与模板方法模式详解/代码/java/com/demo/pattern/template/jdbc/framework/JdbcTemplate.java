package com.demo.pattern.template.jdbc.framework;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public final List<?> executeQuery(String sql, RowMapper<?> rowMapper, Object[] values) throws SQLException {
        // 1, 获取数据源
        Connection conn = this.getConnection();
        // 2, 创建语句集
        PreparedStatement pstm = this.createPreparedStatement(conn, sql);
        // 3, 执行语句集
        ResultSet rs = this.executeQuery(pstm, values);

        // 4, 处理结果集
        List<?> result = this.parseResultSet(rs, rowMapper);
        rs.close();
        //
        pstm.close();
        //
        conn.close();
        return result;
    }

    private List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws SQLException {
        List<Object> result = new ArrayList<Object>();
        int rowNum = 1;
        while (rs.next()){
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return null;
    }

    private ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            pstm.setObject(i, values[i]);
        }
        return pstm.executeQuery();
    }


    private PreparedStatement createPreparedStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
