package annotaion;


import cn.annotaion.dao.IUserDao;
import cn.annotaion.domain.User;
import cn.xmlcustom.mybaties.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        InputStream in = Resources.getResourceAsStream("myMapConfig-annotation.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession(true);
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 测试后资源释放
     */
    @After
    public void destroy() throws IOException {
        if(sqlSession != null){
            try {
                sqlSession.commit();
                sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(in != null){
            in.close();
        }
    }

    @Test
    public void findAllTest(){
        List<User> list = userDao.findAll();
        for(User item: list){
            System.out.println(item);
        }
    }

    @Test
    public void findByIdTest(){
        User user = userDao.findById(1);
        System.out.println(user);
    }

    @Test
    public void saveTest(){
        User user = new User();
        user.setUsername("张三");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("中国");
        int id = userDao.save(user);
        System.out.println(user.getId());
    }

    @Test
    public void deleteTest(){
        userDao.delete(8);
    }

    @Test
    public void updateTest(){
        User u =new User();
        u.setId(3);
        u.setUsername("李四");
        userDao.update(u);
    }

    @Test
    public void totalTest(){
        System.out.println(userDao.total());
    }

    @Test
    public void findByUsernameTest(){
        System.out.println(userDao.findByUsername("%李%"));
    }
    /**
     * 测试前的初始化
     * @throws IOException
     */

}
