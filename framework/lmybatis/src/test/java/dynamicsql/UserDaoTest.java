package dynamicsql;


import cn.dynamicsql.dao.IUserDao;
import cn.dynamicsql.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;


    @Test
    public void testFindById(){
        User user = userDao.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindByCondition(){
        User user = new User();
        user.setAddress("中国");
        user.setSex("男");
        user.setId(1);
        User condition = userDao.findByCondition(user);
        System.out.println(condition);
    }

    @Test
    public void testFindBylis(){
        List<Integer> lis = new ArrayList();

        List<User> condition = userDao.findBylis(new int[]{1,2});
        System.out.println(condition);
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
