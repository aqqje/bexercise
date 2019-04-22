package cn.aqqje.cn.aqqje.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DataSource ds;

    static{
        // 加载连接池配置文件
        try {
            Properties ps = new Properties();
            InputStream resource = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            ps.load(resource);
            // 配置连接池数据
            ds = DruidDataSourceFactory.createDataSource(ps);
        }catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接池对象
     * @return
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
