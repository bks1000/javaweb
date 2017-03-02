package com.june.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class HomeController {

	@RequestMapping("/main")
	public String home() {
		return "master";
	}

	@RequestMapping("/getpage")
	public String home2(HttpServletRequest request, HttpServletResponse response){
		System.out.println("ftl");
		return  "index";
	}
}
