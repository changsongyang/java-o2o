package com.taotao.service;

import java.util.List;

import com.taotao.commom.pojo.EasyUITreeRusult;

public interface ItemCatService {
	List<EasyUITreeRusult> getItemCat(long parentId);
}
