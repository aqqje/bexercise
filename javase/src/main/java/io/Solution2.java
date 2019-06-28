package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author AqqJe
 * @description FileInputStream 类学习
 */
public class Solution2 {

    private static final String PATH = "E:\\github\\bexercise\\javase\\src\\main\\java\\io\\a.text";

    public static void main(String[] args) throws IOException {
        // 使用File对象创建流对象
        File f = new File(PATH);
        FileInputStream fis1 = new FileInputStream(f);
        // 使用文件名称创建流对象
        FileInputStream fis2 = new FileInputStream(PATH);
        // 读取下一个字节
        int read = fis2.read();
        System.out.println((char)read);

        System.out.println("=========");

        // 读取所有字节
        // 定义变量，保存数据
        int b;
        // 循环读取
        while((b = fis2.read()) != -1){
            System.out.print((char) b);
        }

        System.out.println("=========");
        // 定义字节数组，作为装字节数据的容器
        byte[] b1 = new byte[2];
        // 定义变量，作为有效个数
        int len;
        while((len = fis2.read(b1)) != -1){
            System.out.print(new String(b1));
        }

        // 释放资源
        fis1.close();
        fis2.close();
    }
}
