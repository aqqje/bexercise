package com.aqqje.springannotation.project.Controller;

import com.aqqje.springannotation.project.Server.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author AqqJe
 * @Date 2020/8/11
 * @Version 1.0
 **/
@Controller
public class MyController {

    @Autowired
    private MyService myService;
}
