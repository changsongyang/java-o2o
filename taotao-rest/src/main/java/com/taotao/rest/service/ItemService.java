package com.taotao.rest.service;

import com.taotao.po.TbItem;
import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemParamItem;

public interface ItemService {
	
	TbItem getItemById(Long itemId);
	
	TbItemDesc getItemDesc(Long itemId);
	
	TbItemParamItem getItemParamById(Long itemId);
	
}
