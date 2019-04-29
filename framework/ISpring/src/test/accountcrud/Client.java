package accountcrud;

import cn.accountcrud.configuration.SpringConfiguration;
import cn.accountcrud.dao.IAccountDao;
import cn.accountcrud.domain.Account;
import cn.accountcrud.serivce.IAccountService;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring-accountcrud.xml"})
@ContextConfiguration(classes = {SpringConfiguration.class})
public class Client {
    /*private ApplicationContext context;
    private IAccountService accountService;*/
    @Autowired
    @Qualifier("accountService")
    private IAccountService accountService;

    @Autowired
    @Qualifier("accountDao")
    private IAccountDao accountDao;

    @Test
    public void isSingletonTest(){
        /*QueryRunner runner1 = (QueryRunner) context.getBean("runner");
        QueryRunner runner2 = (QueryRunner) context.getBean("runner");
        System.out.println(runner1 == runner2);*/
    }

    @Test
    public void findAllTest(){
        List<Account> list = accountService.findAll();
        for (Account account:list) {
            System.out.println(account);
        }
    }

    @Test
    public void saveTest(){
        Account account = new Account();
        account.setUid(2);
        account.setMoney(500);
        accountService.save(account);
    }

    @Test
    public void deleteTest(){
        accountService.delete(19);
    }

    @Test
    public void updateTest(){
        Account account = new Account();
        account.setId(6);
        account.setMoney(123456);
        accountService.update(account);
    }

  /*  @Before
    public void init(){
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        accountService = (IAccountService)context.getBean("accountService");
    }*/

}
