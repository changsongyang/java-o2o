package com.taotao.service;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbContent;

public interface ContentService {
	
	EasyUIDataGridResult getContentList(Long categoryId,int page, int rows);
	
	TaotaoResult sevaContent(TbContent  tbContent);
	
	TaotaoResult updateContent(TbContent tbContent);

	TaotaoResult deleteContent(Long ids);
	
}
