package com.taotao.service;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbItem;

public interface ItemService {
	
	EasyUIDataGridResult getItemList(int page, int rows); 
	
	TaotaoResult createItem(TbItem item,String desc,String itemParams);
	
	TaotaoResult deleteItemId(Long id);
	
	TaotaoResult reshelf(Long id);
	
	TaotaoResult instock(Long id);
}
