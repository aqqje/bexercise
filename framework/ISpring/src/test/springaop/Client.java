package springaop;

import cn.springaop.dao.IAccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-aop.xml")
public class Client {

    @Autowired
    private IAccountDao accountDao;

    @Test
    public void saveTest(){
        accountDao.save();
    }
}
