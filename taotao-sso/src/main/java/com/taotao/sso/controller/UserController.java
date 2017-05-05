package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/showRegister")
	public String register(){
		return "register";
	}
	
}
