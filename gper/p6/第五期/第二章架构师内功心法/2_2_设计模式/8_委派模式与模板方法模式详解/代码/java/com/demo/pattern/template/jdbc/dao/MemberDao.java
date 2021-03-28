package com.demo.pattern.template.jdbc.dao;

import com.demo.pattern.template.jdbc.entity.Member;
import com.demo.pattern.template.jdbc.framework.JdbcTemplate;
import com.demo.pattern.template.jdbc.framework.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MemberDao extends JdbcTemplate {

    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll() throws SQLException {
        String sql = " SELECT * FROM T_MEMBER";
        return super.executeQuery(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                // 字段过多原型模式
                member.setUsername(rs.getString("username"));
                member.setAddr(rs.getString("password"));
                member.setAge(rs.getString("age"));
                member.setPassword(rs.getString("password"));
                return member;
            }
        }, null);
    }
}
