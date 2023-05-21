package com.learning.webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public String SayHello() {
		return "Welcome! to the first controller";
	}

	@RequestMapping("/helloJSP")
	public String SayHelloJSP() {
		return "SayHello"; // Name of JSP is returned
	}

	@RequestMapping("/helloHtml")
	@ResponseBody
	public String SayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>");
		sb.append("My first HTML page using String Buffer");
		sb.append("</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first HTML page using String Buffer");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
