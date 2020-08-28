package com.aqqje.v1.spring.framework.Controller;

import com.aqqje.v1.spring.framework.annotation.AQAutowired;
import com.aqqje.v1.spring.framework.annotation.AQController;
import com.aqqje.v1.spring.framework.annotation.AQRequestMapping;
import com.aqqje.v1.spring.framework.annotation.AQRequestParam;
import com.aqqje.v1.spring.framework.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//虽然，用法一样，但是没有功能
@AQController
@AQRequestMapping("/demo")
public class DemoAction {

  	@AQAutowired
	private IDemoService demoService;

	@AQRequestMapping("/query")
	public void query(HttpServletRequest req, HttpServletResponse resp,
					  @AQRequestParam("name") String name){
		String result = demoService.get(name);
//		String result = "My name is " + name;
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AQRequestMapping("/add")
	public void add(HttpServletRequest req, HttpServletResponse resp,
                    @AQRequestParam("a") Integer a, @AQRequestParam("b") Integer b){
		try {
			resp.getWriter().write(a + "+" + b + "=" + (a + b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AQRequestMapping("/sub")
	public void sub(HttpServletRequest req, HttpServletResponse resp,
                    @AQRequestParam("a") Double a, @AQRequestParam("b") Double b){
		try {
			resp.getWriter().write(a + "-" + b + "=" + (a - b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AQRequestMapping("/remove")
	public String remove(@AQRequestParam("id") Integer id){
		return "" + id;
	}

	public static void main(String[] args) {
		System.out.println("dddddddddddddd");
	}
}
