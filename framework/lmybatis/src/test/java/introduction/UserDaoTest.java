package introduction;

import cn.introduction.dao.IUserDao;
import cn.introduction.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

public class UserDaoTest {

    private SqlSession sqlSession;
    private Reader reader;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("myMapConfig-introduction.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(reader);
        SqlSession sqlSession = sessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
    }

    @Test
    public void findAllTest(){
        List<User> list = userDao.findAll();
        System.out.println(list);
    }

    /**
     * 测试后资源释放
     */
    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        if(reader != null){
            reader.close();
        }
    }
}
