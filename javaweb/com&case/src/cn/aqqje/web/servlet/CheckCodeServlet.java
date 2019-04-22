package cn.aqqje.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");


        int width  = 80;
        int height = 30;
        // 创建画笔对象
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // 创建一个画布
        g.setColor(Color.gray);
        g.fillRect(0,0,width, height);
        // 生成4位随机字符验证码并存在sessio域中
        String checkCode = getCheckCode();
        request.getSession().setAttribute("CHECKCODE_SERVER", checkCode);
        // 生成图片
        g.setColor(Color.yellow);
        g.setFont(new Font("黑体", Font.BOLD, 24));
        g.drawString(checkCode,15,24);
        // 输出图片
        ImageIO.write(image, "PNG", response.getOutputStream());
    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        String base = "0123456789ABCDEFGabcdefg";
        for (int i = 0; i < 4; i++) {
            sb.append(base.charAt(r.nextInt(base.length())));
        }
        return sb.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
