package subtitle;

import java.io.*;

/**
 * create by 2020/05/18
 */
public class SubtitleUtils {

    public static void main(String[] args) throws Exception {
        StringBuffer sb = new StringBuffer();

        /**读取文件 */
        File strFile = new File("C:\\Users\\Administrator\\Desktop\\CHS_庆余年2-4.txt");
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
        File file = new File("C:\\Users\\Administrator\\Desktop\\CHS_2-4_文稿.txt");
        FileOutputStream fos = new FileOutputStream(file, true);
        byte[] fosword = str.getBytes();
        fos.write(fosword);
        fis.close();
        fos.close();
    }

    public void qynDictFilter(String str){
        str.replaceAll("思黎里", "司理理");
        str.replaceAll("朱砂", "诛杀");
        str.replaceAll("检察院", "监查院");
        str.replaceAll("性地", "庆帝");
        str.replaceAll("男性", "南庆");
        str.replaceAll("本溪", "北齐");
        str.replaceAll("起飞", "岂非");
        str.replaceAll("范贤", "范闲");
        str.replaceAll("森林里", "司理理");
        str.replaceAll("四厘米", "司理理");
        str.replaceAll("篮下", "拦下");
        str.replaceAll("拉下吗", "拉下马");
    }
}
