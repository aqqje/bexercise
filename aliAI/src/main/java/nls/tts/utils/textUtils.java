package nls.tts.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class textUtils {

    /** 文本分割 */
    public static List<String> breakDown(String text, int step){
        String subStr = "";
        List<String> texts = new ArrayList<String>();
        int index = 0;
        while(index  != -1){
            if(index + step >= text.length()){
                subStr = text.substring(index, text.length());
                index = -1;
            }else{
                subStr = text.substring(index, index+step);
                index += step;
            }
            texts.add(subStr);
        }
        return texts;
    }
    /** 文本文件处理 */
    public static String textFileRead(String fileRul){
        File file = new File(fileRul);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int lenght = 0;
            StringBuffer sb = new StringBuffer();
            while((lenght = fis.read(buf)) != -1){
                sb.append(new String(buf,0, lenght));
            }
            return sb.toString().replaceAll(" ", "");
        }catch (Exception e){
            return null;
        }finally {
            try {
                if(fis != null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
