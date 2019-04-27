package cn.xmlcustom.mybaties.io;

import java.io.InputStream;

/**
 * 加载配置文件
 */
public class Resources {
    /**
     *使用类加载器配置文件为字符流对象
     * @param
     * @return
     */
    public static InputStream getResourceAsStream(String filepath) {
        return Resources.class.getClassLoader().getResourceAsStream(filepath);
    }
}
