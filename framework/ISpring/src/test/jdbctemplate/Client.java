package jdbctemplate;

import cn.jdbctemplate.domain.Account;
import cn.jdbctemplate.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-jdbctemplate.xml")
public class Client {

    @Autowired
    private IAccountService accountService;

    @Test
    public void findAllTest(){
        List<Account> list = accountService.findAll();
        for (Account item: list) {
            System.out.println(item);
        }
    }

    @Test
    public void findById(){
        System.out.println(accountService.findById(3));
    }

    @Test
    public void delete(){
        accountService.delete(3);
    }

    @Test
    public void save(){
        Account a = new Account();
        a.setMoney(66666);
        a.setUid(2);
        accountService.save(a);
    }

    @Test
    public void update(){
        Account a = new Account();
        a.setMoney(66666);
        a.setId(8);
        accountService.update(a);
    }

    @Test
    public void findAlldis(){
        int i= accountService.findAlldis();
        System.out.println(i);
    }
}
