package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemDetailsController {
	@RequestMapping("/item/{id}")
	public String itemDetails(@PathVariable Long id){
		return "item";
	}
	
}
