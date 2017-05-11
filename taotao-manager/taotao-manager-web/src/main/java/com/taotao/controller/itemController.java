package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult itemSave(TbItem item,String desc,String itemParams){
		TaotaoResult result = itemService.createItem(item, desc,itemParams);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult delete(@RequestParam Long ids){
		TaotaoResult result = itemService.deleteItemId(ids);
		return result;
	}
	
	@RequestMapping("/reshelf")
	@ResponseBody
	public TaotaoResult reshelf(@RequestParam Long ids){
		TaotaoResult result = itemService.reshelf(ids);
		return result;
	}
	
	@RequestMapping("/instock")
	@ResponseBody
	public TaotaoResult instock(@RequestParam Long ids){
		TaotaoResult result = itemService.instock(ids);
		return result;
	}
}
