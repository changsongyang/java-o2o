package com.taotao.service;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;

public interface ItemParamService {
	
	EasyUIDataGridResult getItemParamList(int page, int rows); 
	
	TaotaoResult getItemParamById(Long id);
	
	TaotaoResult insertItemParam(Long cid,String ParamData);
}
