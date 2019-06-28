package io;

import java.io.*;

/**
 * @author AqqJe
 * @description 复制图片文件
 */
public class Solution3 {

    /**
     * 复制图片文件
     * @param sources 源文件
     * @param target 目标文件
     * @return 是否成功
     * @throws IOException
     */
    public static boolean copyImg(String sources, String target) throws IOException {
        boolean flag = false;
        File source = new File(sources);
        FileInputStream fis = new FileInputStream(source);
        File tar = new File(target);
        FileOutputStream fos = new FileOutputStream(tar);
        byte[] b = new byte[1024];
        int len;
        while((len = fis.read(b)) != -1){
            fos.write(b, 0, len);
            flag = true;
        }
        fos.close();
        fis.close();
        return flag;
    }

    // 测试
    public static void main(String[] args) throws IOException {
        System.out.println(copyImg("C:\\Users\\Administrator\\Desktop\\aqqje.jpg", "C:\\Users\\Administrator\\Desktop\\aqqje1.jpg"));
    }
}
