package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbItem;
import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemParamItem;
import com.taotao.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TaotaoResult getItemId(@PathVariable Long itemId){
		try {
			TbItem result = itemService.getItemById(itemId);
			return TaotaoResult.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId){
		try {
			TbItemDesc result = itemService.getItemDesc(itemId);
			return TaotaoResult.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	

	@RequestMapping("/sprc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemSprc(@PathVariable Long itemId){
		try {
			TbItemParamItem result = itemService.getItemParamById(itemId);
			return TaotaoResult.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
}
