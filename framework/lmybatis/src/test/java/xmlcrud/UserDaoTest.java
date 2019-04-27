package xmlcrud;

import cn.xmlcrud.dao.IUserDao;
import cn.xmlcrud.domain.User;
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

public class UserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;


    @Test
    public void testFindById(){
//        User user = userDao.findById(1);

        User user = sqlSession.selectOne("cn.xmlcrud.dao.IUserDaofindById", 1);
        System.out.println(user);
        System.out.println(user);
    }

    /**
     * 测试前的初始化
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        InputStream in = Resources.getResourceAsStream("myMapConfig-xmlcustom.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
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
