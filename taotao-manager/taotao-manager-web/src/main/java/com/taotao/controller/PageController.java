package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.service.ItemService;

@Controller
public class PageController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(int page, int rows){
		System.out.println(page);
		EasyUIDataGridResult result = itemService.getItemLise(page, rows);
		return result;
	}

}
