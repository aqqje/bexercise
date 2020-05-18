package subtitle;

import java.io.*;

/**
 * create by 2020/05/18
 */
public class SubtitleUtils {

    public static void main(String[] args) throws Exception {
        StringBuffer sb = new StringBuffer();

        /**读取文件 */
        File strFile = new File("C:\\Users\\Administrator\\Desktop\\CHS_庆余年2-3.txt");
        FileInputStream fis = new FileInputStream(strFile);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        while((line = br.readLine()) != null){
            sb.append(line);
        }

        /**格式化 */
        String str = sb.toString();
        str = str.replaceAll("-->", "");
        str = str.replaceAll(",", "");
        str = str.replaceAll(":", "");
        str = str.replaceAll("\\s*", "");
        str = str.replaceAll("\\d", ",");
        str = str.replaceAll(",+", "，");
        str = str.replaceAll("，", "，\n");

        /** 写出*/
        File file = new File("C:\\Users\\Administrator\\Desktop\\CHS_2-3_文稿.txt");
        FileOutputStream fos = new FileOutputStream(file, true);
        byte[] fosword = str.getBytes();
        fos.write(fosword);
        fis.close();
        fos.close();
    }
}
