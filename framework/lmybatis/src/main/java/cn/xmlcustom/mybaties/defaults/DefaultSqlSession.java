package cn.xmlcustom.mybaties.defaults;

import cn.xmlcustom.mybaties.cfg.Configuration;
import cn.xmlcustom.mybaties.proxy.MapperProxy;
import cn.xmlcustom.mybaties.sqlSession.SqlSession;
import cn.xmlcustom.mybaties.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection conn;

    public DefaultSqlSession(Configuration cfg) throws SQLException, ClassNotFoundException {
        this.cfg = cfg;
        this.conn = DataSourceUtil.getConncetion(cfg);
    }

    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(), new Class[]{daoInterfaceClass}, new MapperProxy(cfg.getMappers(), conn));

    }

    @Override
    public void close() {
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
