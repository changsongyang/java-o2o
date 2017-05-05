package com.taotao.portal.service;

import com.taotao.po.TbItem;

public interface ItemService {
	
	TbItem getItemById(Long itemId);
	
	String getItemDesc(Long itemId);
	
	String getItemParamById(Long itemId);
	
}
