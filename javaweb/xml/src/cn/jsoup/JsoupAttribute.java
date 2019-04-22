package cn.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupAttribute {
    public static void main(String[] args) throws IOException {
        String path = JsoupAttribute.class.getClassLoader().getResource("student.xml").getPath();
        // 解析 xml 文档生成 Dom 树
        Document document = Jsoup.parse(new File(path), "utf-8");
        // getElementsByAttribute​(String key)：根据属性名称获取元素对象集合
//        Elements elements = document.getElementsByAttribute("number");
        // getElementsByAttributeValue​(String key, String value)：根据对应的属性名和属性值获取元素对象集合
        Elements elements = document.getElementsByAttributeValue("number", "aqqje_0001");
        System.out.println(elements);
        System.out.println("=================");
        String name = elements.get(0).attr("number");
        System.out.println(name);
        System.out.println("=================");
        System.out.println(elements.text());
    }
}
