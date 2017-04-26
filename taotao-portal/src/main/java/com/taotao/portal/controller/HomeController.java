package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbContent;
import com.taotao.portal.service.ContentService;

@Controller
public class HomeController {

	@Autowired
	ContentService contentService;

	@RequestMapping("/index")
	public String home(Model model) {
		String json = contentService.getContentList(new Long(33));
		// json 转java对象
		TaotaoResult formatToList = TaotaoResult.formatToList(json, TbContent.class);
		List<TbContent> result = (List<TbContent>) formatToList.getData();
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (TbContent tbContent : result) {
			Map<String, Object> item = new HashMap<>();
			item.put("src", tbContent.getPic());
			item.put("width", 670);
			item.put("height", 240);
			item.put("srcB", tbContent.getPic2());
			item.put("widthB", 550);
			item.put("heightB", 240);
			item.put("href", tbContent.getUrl());
			item.put("alt", tbContent.getTitle());
			resultList.add(item);
		}
		//java对象转json字符
		String objectToJson = JsonUtils.objectToJson(resultList);
		model.addAttribute("data", objectToJson);
		return "index";
	}
	
	
	
}
