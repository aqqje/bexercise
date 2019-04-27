package cn.xmlcustom.mybaties.sqlSession;

import java.sql.SQLException;

/**
 * SqlSession 工厂类
 */
public interface SqlSessionFactory {
    /**
     * 打开一个 SqlSession
     * @return
     */
    SqlSession openSession() throws SQLException, ClassNotFoundException;
}
