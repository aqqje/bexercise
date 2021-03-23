package com.demo.pattern.proxy.dynamicproxy.gpproxy.proxy;


import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;

public class GPPorxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader,
                                          Class<?>[] interfaces,
                                          GPInvocationHandler h){
        try {
            // 1, 动态生成源码.java文件
            String src = generateSrc(interfaces);

            // 2, Java文件输出到磁盘，保存为文件$Proxy0.java

            String filePath = GPPorxy.class.getResource("").getPath();
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
            Constructor<?> constructor = clazz.getDeclaredConstructor();
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
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("public class $Proxy0 implements "+interfaces[0].getName()+"{" + ln);
        sb.append(" private IPerson target; " + ln);
        sb.append("}" + ln);
        return sb.toString();
    }
}

