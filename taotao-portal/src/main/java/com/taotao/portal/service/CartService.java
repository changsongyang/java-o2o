package com.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.TaotaoResult;

public interface CartService {
	TaotaoResult cartItem(Long itemId,Integer itemNum,HttpServletRequest request,HttpServletResponse response);
}
