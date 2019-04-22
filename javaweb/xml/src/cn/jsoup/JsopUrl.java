package cn.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsopUrl {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.aqqje.com");
        Document document = Jsoup.parse(url, 10000);
        System.out.println(document);
    }
}
