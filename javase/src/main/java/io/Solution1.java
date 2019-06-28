package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author AqqJe
 * @description FileOutpustream 类学习
 */
public class Solution1 {

    private static final String PATH = "E:\\github\\bexercise\\javase\\src\\main\\java\\io\\a.text";

    public static void main(String[] args) throws IOException {
        // 使用File对象创建流对象
        File f = new File(PATH);
        FileOutputStream fos1 = new FileOutputStream(f);
        // 使用文件名称创建流对象
        FileOutputStream fos2 = new FileOutputStream(PATH);

        // 写出数据
        fos1.write(61);
        fos1.write(333);

        // 字符串转换为字节数组
        byte[] b = "自命不凡".getBytes();
        fos1.write(b);

        // 写出从索引0开始，读取b.length个字节
        fos1.write(b, 0, b.length);


        // 使用文件名称创建流对象,能追加
        FileOutputStream fos3 = new FileOutputStream(PATH, true);

        fos3.write(b);

        //写出换行
        byte[] b1 = {99, 100, 101, 102, 103};
        for(int i = 0; i < b1.length; i++){
            fos3.write(b1[i]);
            fos3.write("\r\n".getBytes());
        }

        // 释放资源
        fos1.close();
        fos2.close();
        fos3.close();

    }
}
