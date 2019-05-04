package cn.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 模拟新增
     */
    @RequestMapping(value = "/user.do", method = RequestMethod.POST)
    public String restPost(){
        System.out.println("restPost:模拟新增");
        return "redirect:../restful.jsp";
    }
    /**
     * 模拟查询
     */
    @RequestMapping(value = "/user/{id}.do", method = RequestMethod.GET)
    public String restGet(@PathVariable("id") int id){
        System.out.println("restGet:模拟查询");
        return "redirect:../../restful.jsp";
    }
    /**
     * 模拟修改
     */
    @RequestMapping(value = "/user/{id}.do", method = RequestMethod.PUT)
    public String restPut(@PathVariable("id") int id){
        System.out.println("restPut:模拟修改");
        return "redirect:../../restful.jsp";
    }
    /**
     * 模拟删除
     */
    @RequestMapping(value = "/user/{id}.do", method = RequestMethod.DELETE)
    public String restDelete(@PathVariable("id") int id){
        System.out.println("restDelete:模拟删除");
        return "redirect:../../restful.jsp";
    }
}
