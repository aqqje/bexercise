package cn.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsopStr {
    public static void main(String[] args) throws IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "\n" +
                " <students>\n" +
                " \t<student number=\"aqqje_0001\">\n" +
                " \t\t<name>tom</name>\n" +
                " \t\t<age>18</age>\n" +
                " \t\t<sex>male</sex>\n" +
                " \t</student>\n" +
                "\t<student number=\"aqqje_0002\">\n" +
                "\t\t<name>aqqje</name>\n" +
                "\t\t<age>11</age>\n" +
                "\t\t<sex>female</sex>\n" +
                "\t</student>\n" +
                " </students>";
        // 解析字符串成 dom 树
        Document document = Jsoup.parse(xml);
        System.out.println(document);
    }
}
