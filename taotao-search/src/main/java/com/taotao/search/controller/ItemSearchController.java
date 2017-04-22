package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.search.service.ItemSearchService;

@Controller
public class ItemSearchController {
	
	@Autowired
	ItemSearchService itemSearchService;
	
	@RequestMapping(value="/imporSolr",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult imporSolr() throws Exception{
		TaotaoResult result = itemSearchService.getItemList();
		return result;
	}
	
}
