package com.taotao.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class Cartcontroller {
	@Autowired
	CartService cartService;
	
	@RequestMapping("/add/{itemId}")
	public String addItem(@PathVariable Long itemId,Integer itemNum,HttpServletRequest request,HttpServletResponse response){
		cartService.cartItem(itemId, itemNum, request, response);
		return "cart-success";
	}
}
