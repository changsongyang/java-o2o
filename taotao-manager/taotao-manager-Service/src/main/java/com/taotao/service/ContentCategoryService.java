package com.taotao.service;

import java.util.List;

import com.taotao.commom.pojo.EasyUITreeRusult;

public interface ContentCategoryService {
	
	List<EasyUITreeRusult> getContentCategoryList(Long parentId);
	
}
