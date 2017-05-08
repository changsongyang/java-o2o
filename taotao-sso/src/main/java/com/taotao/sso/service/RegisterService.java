package com.taotao.sso.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbUser;

public interface RegisterService {
	
	TaotaoResult checkInfo(String param ,Integer type);
	
	TaotaoResult register(TbUser user);
	
}
