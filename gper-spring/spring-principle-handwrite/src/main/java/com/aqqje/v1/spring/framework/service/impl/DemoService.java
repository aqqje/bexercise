package com.aqqje.v1.spring.framework.service.impl;


import com.aqqje.v1.spring.framework.annotation.AQService;
import com.aqqje.v1.spring.framework.service.IDemoService;

/**
 * 核心业务逻辑
 */
@AQService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
