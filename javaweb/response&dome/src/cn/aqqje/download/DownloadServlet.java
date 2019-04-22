package cn.aqqje.download;

import cn.aqqje.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取下载文件请求的参数
        request.setCharacterEncoding("utf-8");
        String filename = new String(request.getParameter("filename").getBytes("iso-8859-1"), "utf-8");
        System.out.println(filename);
        // 2.获取文件资源的路径
        ServletContext context = request.getServletContext();
        String realPath = context.getRealPath("/file/" + filename);
        System.out.println(realPath);
        // 3.获取输入流加载文件进行内存
        FileInputStream fis = new FileInputStream(realPath);

        // A.设置响应头
        // a.响应头类型
        String mimeType = context.getMimeType(filename);
        response.setHeader("content-type",mimeType);

        // B.针对不浏览器设置不同的编码方式
        String agent = request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(agent, filename);
        System.out.println(filename);


        // b.响应头打开方式
        response.setHeader("content-disposition", "attachment;filename="+filename);
        System.out.println(filename);
        // 4.使用输出流下载文件
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024*8];
        int len = 0;
        while( (len = fis.read(buff)) != -1 ){
            sos.write(buff, 0, len);
        }

        // 5.关闭资源
        fis.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
