package cn.jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsoupXpath {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        String path = JsoupXpath.class.getClassLoader().getResource("student.xml").getPath();
        // 解析 xml 文档生成 Dom 树
        Document document = Jsoup.parse(new File(path), "utf-8");

        // 根据document 对象创建 Xpath 对象
        JXDocument jxDocument = new JXDocument(document);

        //4.1查询所有student标签
        List<JXNode> jxNodes = jxDocument.selN("//student");
        for (JXNode jxNode: jxNodes){
            System.out.println(jxNode);
        }
        System.out.println("===================");
        //4.2查询所有student标签下的name标签
        List<JXNode> jxNodes1 = jxDocument.selN("//student/name");
        System.out.println(jxNodes1);
        System.out.println("===================");
        //4.3查询student标签下带有id属性的name标签
        JXNode jxNode = jxDocument.selNOne("//student/name[@id]");
        System.out.println(jxNode);
        System.out.println("===================");
        //4.4查询student标签下带有id属性的name标签 并且id属性值为aqqje
        JXNode jxNode1 = jxDocument.selNOne("//student/name[@id='aqqje']");
        System.out.println(jxNode1);
        System.out.println("===================");
        Object o = jxDocument.selOne("//students/student");
        System.out.println(o);
    }
}
