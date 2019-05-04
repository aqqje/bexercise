package cn.introduction.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转化器(需要实现 Converter 接口)
 */
public class StringToDateConverter implements Converter<String, Date> {

    /**
     * 字符转日期转化器
     * @param dateStr
     * @return
     */
    @Override
    public Date convert(String dateStr) {
        return dateStrToDate(dateStr);
    }

    public static Date dateStrToDate(String dateStr){
        DateFormat df = null;
        Date date = null;
        // 字符串为空
        if(dateStr == null || "null".equals(dateStr) || "".equals( dateStr)){
            return date;
        }else{
            // 字符串不为空
            //2012-12-12类型
            if(dateStr.contains("-")){
                df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = df.parse(dateStr);
                } catch (ParseException e) {
                    throw new RuntimeException("类型转化异常!");
                }
            }else if(dateStr.contains("/")){
                df = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    date = df.parse(dateStr);
                } catch (ParseException e) {
                    throw new RuntimeException("类型转化异常!");
                }
            }
        }
        return date;
    }
}
