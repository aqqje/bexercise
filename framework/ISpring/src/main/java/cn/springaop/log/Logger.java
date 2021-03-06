package cn.springaop.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("logger")
@Aspect // 表示这一个切面
public class Logger {

    @Pointcut("execution( * cn.springaop.dao.Impl.*.*(..) )") // 表示一个切点
    public void pt1(){}

    /*前置通知*/
    @Before("pt1()")
    public void before(){
        System.out.println("prelogger.........");
    }
    /*后置通知*/
    @AfterReturning("pt1()")
    public void afterReturning(){
        System.out.println("afterReturning.........");
    }
    @AfterThrowing("pt1()")
    /*异常通知*/
    public void afterThrowing(){
        System.out.println("afterThrowing.........");
    }
    @After("pt1()")
    /*最终通知*/
    public void after(){
        System.out.println("after.........");
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决：
     *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     * spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */

//    @Around("pt1()")
    public Object around(ProceedingJoinPoint pjp){
        Object obj = null;
        try {
            System.out.println("around.........前置");
            Object[] args = pjp.getArgs();
            obj = pjp.proceed();// 明确调用业务层方法,(切入点方法)
            System.out.println("around.........后置");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("around.........异常");
        }finally {
            System.out.println("around.........最终");
        }
        return obj;
    }
}
