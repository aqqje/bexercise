package cn.aqqje.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JDBCUtil {
    private static DataSource ds;

    static{
        try {
            Properties pro = new Properties();
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*获取数据库连接池*/
    public static DataSource getDataSource(){
        return ds;
    }
}
