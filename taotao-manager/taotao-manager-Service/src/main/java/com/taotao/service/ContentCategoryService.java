package com.taotao.service;

import java.util.List;

import com.taotao.commom.pojo.EasyUITreeRusult;
import com.taotao.common.utils.TaotaoResult;

public interface ContentCategoryService {
	
	List<EasyUITreeRusult> getContentCategoryList(Long parentId);
	
	TaotaoResult createContentCategory(Long parentId,String name);
	
}
