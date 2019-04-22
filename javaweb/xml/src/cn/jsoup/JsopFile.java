package cn.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsopFile {
    public static void main(String[] args) throws IOException {
        // 获取 student.xml 文件的路径
        String path = JsopFile.class.getClassLoader().getResource("student.xml").getPath();
        // 加载 xml 文件进内存 获取 dom 树 ---> document
        Document document = Jsoup.parse(new File(path),"utf-8");
        // 获取元素对象 Elements
        Elements elements = document.getElementsByTag("name");
        // 获取数据
        Element element = elements.get(0);
        System.out.println(element.text());
    }
}
