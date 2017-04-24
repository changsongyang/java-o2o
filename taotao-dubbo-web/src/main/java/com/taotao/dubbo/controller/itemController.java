package com.taotao.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.dubbo.service.ItemService;

@Controller
@RequestMapping("/item")
public class itemController {
	
	@Autowired
	ItemService itemService;

	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(int page, int rows){
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
}
