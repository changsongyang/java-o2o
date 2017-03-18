package com.taotao.service;

import java.util.List;

import com.taotao.po.TbItem;

public interface ItemService {
	
	public TbItem getTbItemId(Long id);
	
	public List<TbItem> getTbItemList();
	
}
