package com.taotao.search.service;

import com.taotao.commom.pojo.SearchResult;
import com.taotao.common.utils.TaotaoResult;

public interface ItemSearchService {
	
	TaotaoResult getItemList() throws Exception;
	
	SearchResult searchResult(String queryString, int page, int rows) throws Exception;
}
