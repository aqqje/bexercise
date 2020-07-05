package subtitle;

import java.io.*;

/**
 * create by 2020/05/18
 */
public class SubtitleUtils {

    public static void srtToTxt(String sourthPath) throws Exception {

        System.out.println("start ===========");
        StringBuffer sb = new StringBuffer();
        /**读取文件 */
        File strFile = new File(sourthPath);
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
        String path = sourthPath.substring(0, sourthPath.lastIndexOf(".")) + "_文稿" + ".txt";
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file, true);
        byte[] fosword = str.getBytes();
        fos.write(fosword);
        fis.close();
        fos.close();
        System.out.println("end ==================");
    }

    public static void main(String[] args) throws Exception {
        srtToTxt("D:\\自媒体\\视频\\国产：庆余年第二季\\剪辑\\26\\CHS_庆余年2-26.srt");
    }
}
