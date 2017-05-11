package com.taotao.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.TaotaoResult;

public interface LoginService {
	
	TaotaoResult login(HttpServletRequest request, HttpServletResponse response, String username, String password);
}
