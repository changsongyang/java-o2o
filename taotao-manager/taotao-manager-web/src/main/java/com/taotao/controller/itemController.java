package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.EasyUIDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbItem;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class itemController {
	
	@Autowired
	ItemService itemService;

	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(int page, int rows){
		EasyUIDataGridResult result = itemService.getItemLise(page, rows);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult itemSave(TbItem item,String desc){
		TaotaoResult result = itemService.createItem(item, desc);
		return result;
	}
	
}
