package cn.aqqje.test;

import cn.aqqje.util.JDBCUtil;
import org.junit.Test;

public class UtilTest {

    /*数据源的连接*/
    @Test
    public void Test1(){
        System.out.println(JDBCUtil.getDataSource());
    }
}
