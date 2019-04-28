package instroduction;

import cn.instroduction.dao.IAccountDao;
import cn.instroduction.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientTest {
    public static void main(String[] args) {
        /*创建 spring 容器对象*/
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        /*使用 getBean 方法获取具体实体*/
        IAccountDao accountDao = (IAccountDao)context.getBean("accountDao");
        IAccountService accountService = (IAccountService)context.getBean("accountService");
        System.out.println(accountDao);
        System.out.println(accountService);
    }
}
