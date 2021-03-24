package com.demo.pattern.proxy.dynamicproxy.gpproxy.proxy;


import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GPProxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader,
                                          Class<?>[] interfaces,
                                          GPInvocationHandler h){
        try {
            // 1, 动态生成源码.java文件
            String src = generateSrc(interfaces);
            // 2, Java文件输出到磁盘，保存为文件$Proxy0.java

            String filePath = GPProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(file);
            fw.write(src);
            fw.flush();
            fw.close();

            // 3, 把.java文件编译成$Proxy0.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> javaFileObjects = fileManager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, javaFileObjects);
            task.call();
            fileManager.close();
            // 4, 加载到内存
            Class<?> clazz = classLoader.findClass("$Proxy0");
            Constructor<?> constructor = clazz.getDeclaredConstructor(GPInvocationHandler.class);
            Object o = constructor.newInstance(h);
            file.delete();
            return o;
            // 5,返回新的代理对象
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }

    private static String generateSrc(Class<?>[] interfaces){
        StringBuilder sb = new StringBuilder();
        sb.append("package com.demo.pattern.proxy.dynamicproxy.gpproxy.proxy;" + ln);
        sb.append("import com.demo.pattern.proxy.dynamicproxy.jdkproxy.IPerson;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements "+interfaces[0].getName()+"{" + ln);

        sb.append(" GPInvocationHandler h; " + ln);
        sb.append("public $Proxy0 (GPInvocationHandler h){");
            sb.append("this.h = h;" +ln);
        sb.append("}" + ln);

        for (Method method : interfaces[0].getMethods()) {
            /*Class<?>[] parameterTypes = method.getParameterTypes();

            StringBuilder paramNames = new StringBuilder();
            StringBuilder paramValues = new StringBuilder();
            StringBuilder paramClasses = new StringBuilder();*/

            sb.append("public "+ method.getReturnType().getName() + " " + method.getName() + "()" + "{"+ ln);
                sb.append(" try {"+ ln);
                    sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\", new Class[]{} );" + ln);
                    sb.append(" this.h.invoke(this, m, new Object[]{});" + ln);
                    sb.append(" return;");
                sb.append("}" + ln);
                sb.append(" catch(Error _ex) {} "+ ln);
                sb.append(" catch(Throwable throwable) {"+ ln);
                    sb.append(" throw new UndeclaredThrowableException(throwable);\n" + ln);
                sb.append("}");
            sb.append("}" + ln);
        }

        sb.append("}" + ln);
        return sb.toString();
    }
}

