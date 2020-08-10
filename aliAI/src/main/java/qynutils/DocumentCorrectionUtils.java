package qynutils;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.Arrays;

/**
 * 文稿校正工具类
 */
public class DocumentCorrectionUtils {

    /**
     * 根据校正文件内容进行文稿内容校正
     * @param documentSourcePath 文稿路径
     * @param manusriptTextPath 校正文件路径
     */
    public static void CorrectionbyManuscriptText(String documentSourcePath, String manusriptTextPath) throws Exception {
        System.out.println("START....");
        // 读取文稿内容
        // "D:\\自媒体\\视频\\国产：庆余年第二季\\剪辑\\第十一集\\CHS_庆余年2-11.srt"
        FileInputStream fis = new FileInputStream(new File(documentSourcePath));
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        char[] bf =new char[1024];
        StringBuffer sb = new StringBuffer();
        int i = 0;
         while ((i = isr.read(bf)) != -1){sb.append(new String(bf,0,i));}
        String documentSource = sb.toString();
        // 读取校正文件内容
        fis = new FileInputStream(new File(manusriptTextPath));
        isr = new InputStreamReader(fis, "UTF-8");
        sb = new StringBuffer();
        bf =new char[1024];
        i = 0;
        while ((i = isr.read(bf)) != -1){sb.append(new String(bf,0,i));}
        String[] manusriptText = sb.toString()
                .replaceAll("；+",";")
                .replaceAll("\n", ";")
                .replaceAll("\t", ";")
                .replaceAll("\r", ";")
                .replaceAll(";+", ";")
                .split(";");
        // System.out.println(Arrays.toString(manusriptText));
        // 根据文件内容相对内容进行替换
        for (String s : manusriptText) {
            String[] split = s.split("-");
            if(split.length < 2){continue;}
            documentSource = documentSource.replaceAll(split[0], split[1]);
        }
        // 写出校正后的文稿
        String path = documentSourcePath.substring(0, documentSourcePath.lastIndexOf(".")) + "_校正" + documentSourcePath.substring(documentSourcePath.lastIndexOf("."));
        FileOutputStream fos = new FileOutputStream(new File(path), false);
        fos.write(documentSource.getBytes());
        isr.close();
        fis.close();
        fos.close();
        System.out.println("SUCCESS....");
    }

    public static void main(String[] args) throws Exception {
        // CorrectionbyManuscriptText("D:\\自媒体\\视频\\国产：庆余年第二季\\剪辑\\27\\CHS_庆余年2-27.srt", "D:\\自媒体\\视频\\国产：庆余年第二季\\字幕校正\\字幕校正.txt");
        String correctionDocPath = "D:\\自媒体\\视频\\国产：庆余年第二季\\字幕校正\\字幕校正.txt";
        String docResourcePath = "D:\\自媒体\\视频\\国产：庆余年第二季\\剪辑\\%s\\CHS_庆余年2-%s.srt";
        //String docResourcePath = "D:\\自媒体\\视频\\国产：庆余年第二季\\剪辑\\%s\\CHS_庆余年2-%s_校正_文稿.txt";
        for (int i = 33; i <= 38 ; i++) {
            System.out.print(i + ":");
            CorrectionbyManuscriptText(String.format(docResourcePath, String.valueOf(i), String.valueOf(i)), correctionDocPath);
        }
    }
}
