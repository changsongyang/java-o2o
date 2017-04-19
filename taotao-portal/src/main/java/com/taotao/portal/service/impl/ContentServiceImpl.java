package com.taotao.portal.service.impl;

import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Override
	public String getContentList(Long categoryId) {
		String json = HttpClientUtil.doGet("http://localhost:9998/rest/conetent/ipc?categoryId="+categoryId+"");
		return json;
	}

}
