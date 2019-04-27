package cn.xmlcustom.mybaties.proxy;

import cn.xmlcustom.mybaties.cfg.Mapper;
import cn.xmlcustom.mybaties.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * dao 的代理对象
 */
public class MapperProxy implements InvocationHandler {
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法名
        String methodName = method.getName();
        // 限定全类名
        String className = method.getDeclaringClass().getName();
        String key = className+"."+methodName;
        //获取 mapper
        Mapper mapper = mappers.get(key);
        if(mapper == null){
            throw new IllegalArgumentException("传参错误!");
        }
        return new Executor().selectList(mapper, conn);
    }
}
