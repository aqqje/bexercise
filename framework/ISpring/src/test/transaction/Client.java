package transaction;


import cn.transaction.serivce.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-transaction.xml"})
public class Client {

    @Autowired
    @Qualifier("accountService")
    private IAccountService accountService;

     @Autowired
    @Qualifier("accountServiceProxy")
    private IAccountService accountServiceProxy;


    @Test
    public void transferTest(){
        accountServiceProxy.transfer(8,7, 100);
    }

}
