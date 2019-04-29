package cn.transaction.proxy;

import cn.transaction.serivce.IAccountService;
import cn.transaction.transaction.TransationManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * service 的代理工厂
 */
public class BeanFactory {

    private IAccountService accountService;

    private TransationManager tx;

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public void setTx(TransationManager tx) {
        this.tx = tx;
    }

    public IAccountService getAccountService(){
        return (IAccountService)Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object obj = null;
                try {
                    tx.beginTransaction();
                    obj = method.invoke(accountService, args);
                } catch (Exception e) {
                    tx.rollback();
                } finally {
                    tx.release();
                }
                return obj;
            }
        });
    }
}
