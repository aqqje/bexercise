package introduction;

import cn.introduction.dao.IUserDao;
import cn.introduction.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class UserDaoTest {

    private IUserDao userDao;

    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("myMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(reader);
        SqlSession sqlSession = sessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> list = userDao.findAll();
        System.out.println(list);
    }
}
