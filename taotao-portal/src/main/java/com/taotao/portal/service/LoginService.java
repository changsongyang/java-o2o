package com.taotao.portal.service;

import com.taotao.po.TbUser;

public interface LoginService {
	
	TbUser getUserByToken(String token);
}
