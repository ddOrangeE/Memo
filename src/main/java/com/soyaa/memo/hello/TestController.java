package com.soyaa.memo.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@ResponseBody
	@RequestMapping("/hello")
	public String HelloWorld() {
		return "hello world!";
	}
}
