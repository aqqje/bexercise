package cn.aqqje.servlet;

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
        // 设置宽和高
        int width = 100;
        int height = 50;

        // 1.创建图片对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 2.美化图片
        // 设置 bg
        Graphics g = image.getGraphics();// 获取画笔对象
        g.setColor(Color.pink);// 设置画笔颜色
        g.fillRect(0, 0, width, height);

        //画边框
        g.setColor(Color.blue);
        g.drawRect(0, 0, width -1, height - 1);

        // 生成随机字符
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";

        Random random = new Random();

        for (int i = 1; i <= 4 ; i++) {
            // 生成随机角标
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            // 写验证码
            g.drawString(ch+"",width/5*i, height/2);
        }
        g.setColor(Color.green);
        // 画干扰线
        for (int i = 0; i <8 ; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1, x2, y1, y2);
        }

        // 3.输出图片
        ImageIO.write(image, "jpg", response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
