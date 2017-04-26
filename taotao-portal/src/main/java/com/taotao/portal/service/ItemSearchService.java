package com.taotao.portal.service;

import com.taotao.commom.pojo.SearchResult;

public interface ItemSearchService {
	SearchResult getItemList(String keyword, int page, int rows);
}
