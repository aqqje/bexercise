package com.demo.pattern.template.jdbc;

import com.demo.pattern.template.jdbc.dao.MemberDao;
import com.demo.pattern.template.jdbc.entity.Member;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        MemberDao memberDao = new MemberDao(null);
        List<Member> members = (List<Member>) memberDao.selectAll();
    }
}
