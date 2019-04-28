package instroduction;

import cn.instroduction.dao.IAccountDao;
import cn.instroduction.factory.BeanFactory;
import cn.instroduction.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class ClientTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        /*创建 spring 容器对象*/
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        /*使用 getBean 方法获取具体实体*/
        //IAccountDao accountDao = (IAccountDao)context.getBean("accountDao");
        //System.out.println(accountDao);
        //IAccountService accountService = (IAccountService)context.getBean("accountService1");
        //System.out.println(accountService);
        //IAccountService accountService = (IAccountService)context.getBean("accountService4");
        //accountService.save();
        BeanFactory factory = new BeanFactory("bean.properties");
        IAccountService accountService = (IAccountService)factory.getBean("accountService");
        accountService.save();
    }
}
