import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//@WebListener()
public class ContextServletListener implements ServletContextListener{


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {
            /*加载资源*/
            ServletContext servletContext = servletContextEvent.getServletContext();
            String application = servletContext.getInitParameter("application");
            String realPath = servletContext.getRealPath(application);
            FileInputStream fis = new FileInputStream(new File(realPath));
            System.out.println(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("ContextServletListener 被执行了...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
