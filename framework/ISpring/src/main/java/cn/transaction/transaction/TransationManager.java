package cn.transaction.transaction;

import cn.transaction.utils.ConnectionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 事务管理
 */
@Component("tx")
@Aspect
public class TransationManager {

    @Autowired
    private ConnectionUtil connUtil;

    @Pointcut("execution(* cn.transaction.dao.impl.*.*(..))")
    public void tx2(){}

    /*public void setConn(ConnectionUtil connUtil) {
        this.connUtil = connUtil;
    }*/

    /*开启事务*/
//    @Before("tx2()")
    public void beginTransaction(){
        try{
            System.out.println("beginTransaction...");
            connUtil.getThreadConnection().setAutoCommit(false);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /*提交事务*/
//    @AfterReturning("tx2()")
    public void commit(){
        try{
            connUtil.getThreadConnection().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    /*回滚事务*/
//    @AfterThrowing("tx2()")
    public void rollback(){
        try{
            connUtil.getThreadConnection().rollback();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /*释放资源*/
//    @After("tx2()")
    public void release(){
        try{
            connUtil.getThreadConnection().close();
            connUtil.removeConnection();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 基于注解的aop的事务控制不能使用 @before @after @AfterReturning @AfterThrowing, 因为其执行顺序为 1-3-2, 不能控制为单例连接
     * 如果想用则使用 @Around
     */
    /*释放资源*/
    @Around("tx2()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object obj = null;
        try {
            this.beginTransaction();
            Object[] args = pjp.getArgs();
            obj = pjp.proceed(args);
            this.commit();
        } catch (Throwable e) {
            this.rollback();
            throw new RuntimeException(e);
        } finally {
            this.release();
        }
        return obj;
    }
}
