package xmlcustom;

import cn.xmlcustom.dao.IUserDao;
import cn.xmlcustom.domain.User;
import cn.xmlcustom.mybaties.io.Resources;
import cn.xmlcustom.mybaties.sqlSession.SqlSession;
import cn.xmlcustom.mybaties.sqlSession.SqlSessionFactory;
import cn.xmlcustom.mybaties.sqlSession.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {

    public static void main(String[] args) throws IOException, NoSuchMethodException, SQLException, ClassNotFoundException {
        InputStream inputStream = Resources.getResourceAsStream("myMapConfig-xmlcustom.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> list = userDao.findAll();
        System.out.println(list);
        sqlSession.close();
    }

}
