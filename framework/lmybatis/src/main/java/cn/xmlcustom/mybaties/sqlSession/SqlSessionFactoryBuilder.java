package cn.xmlcustom.mybaties.sqlSession;

import cn.xmlcustom.mybaties.cfg.Configuration;
import cn.xmlcustom.mybaties.defaults.DefaultSqlSessionFactory;
import cn.xmlcustom.mybaties.io.Resources;
import cn.xmlcustom.mybaties.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * SqlSessionFactory 构建者
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(inputStream);
        return new DefaultSqlSessionFactory(cfg);
    }
}
