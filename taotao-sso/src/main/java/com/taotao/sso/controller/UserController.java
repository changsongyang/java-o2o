package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbUser;
import com.taotao.sso.service.LoginService;
import com.taotao.sso.service.RegisterService;
import com.taotao.sso.service.TokenService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	RegisterService registerService;
	@Autowired
	LoginService loginService;
	@Autowired
	TokenService tokenService;

	@RequestMapping("/pageLogin")
	public String pageLogin(String redirect,Model model) {
		System.out.println(redirect +"model");
		model.addAttribute("redirect", redirect);
		return "login";
	}

	@RequestMapping("/showRegister")
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/check/{param}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public Object checkInfo(@PathVariable String param, @PathVariable Integer type, String callback) {
		TaotaoResult result = registerService.checkInfo(param, type);
		if (null == callback) {
			return result;
		}
		// 开启jsonp
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult register(TbUser user) {
		try {
			TaotaoResult result = registerService.register(user);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(username)) {
			return TaotaoResult.build(400, "用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			return TaotaoResult.build(400, "密码不能为空");
		}
		try {
			TaotaoResult result = loginService.login(username, password, request, response);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}

	}

	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getToken(@PathVariable String token, String callback) {
		TaotaoResult result = tokenService.geteToken(token);
		if (StringUtils.isBlank(callback)) {
			return result;
		}
		// 开启jsonp
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
	@RequestMapping("/delToken/{token}")
	@ResponseBody
	public Object delToken(@PathVariable String token, String callback) {
		TaotaoResult result = tokenService.deleteToken(token);
		if (StringUtils.isBlank(callback)) {
			return result;
		}
		// 开启jsonp
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
