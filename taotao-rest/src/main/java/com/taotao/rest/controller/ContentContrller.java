package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.service.ContentService;

@Controller
@RequestMapping("/conetent")
public class ContentContrller {
	
	@Autowired
	ContentService contentService;
	
	@RequestMapping("/ipc")
	@ResponseBody
	public TaotaoResult getIpc(Long categoryId){
		TaotaoResult result = contentService.getContentList(categoryId);
		return result;
	} 
	
}
