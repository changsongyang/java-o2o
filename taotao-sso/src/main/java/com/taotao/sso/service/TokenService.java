package com.taotao.sso.service;

import com.taotao.common.utils.TaotaoResult;

public interface TokenService {
	
	TaotaoResult geteToken(String token);
	
	TaotaoResult deleteToken(String token);
}
