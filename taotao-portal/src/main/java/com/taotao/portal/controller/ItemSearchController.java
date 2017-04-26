package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.commom.pojo.SearchResult;
import com.taotao.portal.service.ItemSearchService;

@Controller
public class ItemSearchController {

	@Autowired
	ItemSearchService itemSearchService;

	@RequestMapping("/search")
	public String search(@RequestParam("q") String keyword, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "60") Integer rows, Model model) {
		try {
			keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SearchResult result = itemSearchService.getItemList(keyword, page, rows);
		//直接返回jsp el渲染
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("itemList", result.getItemList());
		model.addAttribute("page", result.getCurPage());
		return "search";
	}

}
