package dynamicsql;


import cn.dynamicsql.dao.IAccountDao;
import cn.dynamicsql.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;

    @Test
    public void testFindAll(){
        List<Account> list = accountDao.findAll();
        for (Account item:list) {
            System.out.println(item);
        }
    }

    /**
     * 测试前的初始化
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        InputStream in = Resources.getResourceAsStream("myMapConfig-dynamicsql.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    /**
     * 测试后资源释放
     */
    @After
    public void destroy() throws IOException {
        if(in != null){
            in.close();
        }
    }
}
