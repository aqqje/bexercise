package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author AqqJe
 * @description FileReader 类学习
 */
public class Solution4 {

    private static final String PATH = "E:\\github\\bexercise\\javase\\src\\main\\java\\io\\a.text";

    public static void main(String[] args) throws IOException {
        // 使用File对象创建流对象
        File file = new File(PATH);
        FileReader fr1 = new FileReader(file);
        // 使用文件名称创建流对象
        FileReader fr2 = new FileReader(PATH);

        int len;
        while((len = fr1.read()) != -1){
            System.out.print((char) len);
        }

        System.out.println("===============");

        // 定义字符数组，作为装字符数据的容器
        char[] c = new char[1024];
        // 定义变量，保存有效字符个数
        int len1;
        while((len1 = fr2.read(c)) != -1){
            System.out.println(new String(c, 0, len1));
        }

        // 释放资源
        fr1.close();;
        fr2.close();
    }
}
