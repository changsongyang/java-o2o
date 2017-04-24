package com.taotao.dubbo.service;

import com.taotao.commom.pojo.EasyUIDataGridResult;

public interface ItemService {
	
	EasyUIDataGridResult getItemList(int page, int rows); 
	
}
