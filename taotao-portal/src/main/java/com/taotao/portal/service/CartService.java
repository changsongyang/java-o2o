package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbItem;

public interface CartService {
	
	TaotaoResult cartItem(Long itemId,Integer itemNum,HttpServletRequest request,HttpServletResponse response);
	List<TbItem> getTbItem(HttpServletRequest request);
	TaotaoResult updateCart(HttpServletRequest request,HttpServletResponse response,Long itemid,Integer num);
	TaotaoResult deleteCart(HttpServletRequest request,HttpServletResponse response,Long itemid);

}
