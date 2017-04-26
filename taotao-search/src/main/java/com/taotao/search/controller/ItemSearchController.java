package com.taotao.search.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.commom.pojo.SearchResult;
import com.taotao.common.utils.ExceptionUtil;
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
	
	@RequestMapping(value="/search")
	@ResponseBody
	public TaotaoResult search(@RequestParam(defaultValue="") String keyword,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="10") int rows){
		
		try {
			keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		try {
			SearchResult result = itemSearchService.searchResult(keyword, page, rows);
			return TaotaoResult.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		
	}
	
}
