package cn.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupSelect {
    public static void main(String[] args) throws IOException {
        String path = JsoupSelect.class.getClassLoader().getResource("student.xml").getPath();
        // 解析 xml 文档生成 Dom 树
        Document document = Jsoup.parse(new File(path), "utf-8");
        Elements elements = document.select("name");
        System.out.println(elements);
        System.out.println("======================");
        //4.查询id值为itcast的元素
        Elements elements1 = document.select("#aqqje");
        System.out.println(elements1);
        System.out.println("======================");
        //5.获取student标签并且number属性值为aqqje_0001的age子标签
        Elements elements2 = document.select("student[number='aqqje_0001'] > age");
        System.out.println(elements2);
        System.out.println("======================");
        //5.1.获取student标签并且number属性值为aqqje_0001
        Elements elements3 = document.select("student[number='aqqje_0001']");
        System.out.println(elements3);
    }
}
