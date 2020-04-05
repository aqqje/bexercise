package com.gper.mvcframework.v1.demo.service.impl;

import com.gper.mvcframework.v1.demo.service.IDemoService;
import com.gper.mvcframework.v1.mvcframework.annotation.GPService;

/**
 * 核心业务逻辑
 */
@GPService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
