package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.commom.pojo.SearchResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.service.ItemSearchService;

@Service
public class ItemSearchServiceImpl implements ItemSearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;

	@Override
	public SearchResult getItemList(String keyword, int page, int rows) {
		Map<String, String> vo = new HashMap<>();
		vo.put("keyword", keyword);
		vo.put("page", page + "");
		vo.put("rows", rows + "");
		String json = HttpClientUtil.doGet(SEARCH_BASE_URL, vo);
		// 转换成java对象
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
		// 取返回的结果
		SearchResult searchResult = (SearchResult) taotaoResult.getData();

		return searchResult;
	}

}
