package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.po.TbItem;
import com.taotao.portal.service.CartService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	CartService cartService;
	
	@RequestMapping("/order-cart")
	public String orderCart(HttpServletRequest request,Model model){
		List<TbItem> list = cartService.getTbItem(request);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String success(){
		return "success";
	}
	
}
