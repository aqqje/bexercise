package accountcrud;

import cn.accountcrud.domain.Account;
import cn.accountcrud.serivce.IAccountService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Client {
    private ApplicationContext context;
    private IAccountService accountService;

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

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring-accountcrud.xml");
        accountService = (IAccountService)context.getBean("accountService");
    }

}
