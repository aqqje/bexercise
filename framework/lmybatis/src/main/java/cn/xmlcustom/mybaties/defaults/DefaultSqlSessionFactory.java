package cn.xmlcustom.mybaties.defaults;

import cn.xmlcustom.mybaties.cfg.Configuration;
import cn.xmlcustom.mybaties.sqlSession.SqlSession;
import cn.xmlcustom.mybaties.sqlSession.SqlSessionFactory;

import java.sql.SQLException;

/**
 * SqlSessionFactory 实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    @Override
    public SqlSession openSession() throws SQLException, ClassNotFoundException {
        return new DefaultSqlSession(cfg);
    }
}
