package cn.aqqje.web.servlet;

import cn.aqqje.domain.Province;
import cn.aqqje.service.ProvinceService;
import cn.aqqje.service.serviceImpl.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAllServlet")
public class FindAllServlet extends HttpServlet {
    private ProvinceService service = new ProvinceServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String proJson = service.findAllJson();
        response.getWriter().write(proJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
