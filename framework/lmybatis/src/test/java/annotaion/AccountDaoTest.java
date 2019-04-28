package annotaion;


import cn.annotaion.dao.IAccountDao;
import cn.annotaion.domain.Account;
import cn.xmlcustom.mybaties.io.Resources;
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

    /**
     * 测试前的初始化
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("myMapConfig-annotation.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession(true);
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    /**
     * 测试后资源释放
     */
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        if(in != null){
            in.close();
        }
    }


    @Test
    public void findAllAndWithUser(){
        List<Account> list = accountDao.findAllAndWithUser();
        for (Account account: list) {
            System.out.println(account);
        }
    }

    @Test
    public void findByUid(){
        List<Account> list = accountDao.findByUid(2);
        for (Account account: list) {
            System.out.println(account);
        }
    }
}
