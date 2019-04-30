package springtx;


import cn.springtx.dao.IAccountDao;
import cn.springtx.domain.Account;
import cn.springtx.serivce.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-springtx.xml"})
//@ContextConfiguration(classes = {config.SpringConfiguration.class})
public class Client {

    @Autowired
    private IAccountDao accountDao;

/*    @Autowired
    @Qualifier("accountService")
    private IAccountService accountService;*/

    @Autowired
    @Qualifier("accountService1")
    private IAccountService accountService1;

    @Test
    public void findByIdTest(){
        Account account = accountDao.findById(3);
        System.out.println(account);
    }

    @Test
    public void updateTest(){
        Account account1 = new Account(9,0,1000);
        Account account2 = new Account(8,0,1000);
        accountDao.update(account1);
        accountDao.update(account2);
    }

/*    @Test
    public void transferTest(){
        accountService.transfer(9, 8, 100);
    }*/

     @Test
    public void transferTest1(){
        accountService1.transfer(9, 8, 100);
    }


}
