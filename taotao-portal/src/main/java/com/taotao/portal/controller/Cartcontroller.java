package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbItem;
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
	@RequestMapping("/cart")
	public String cart(Model model,HttpServletRequest request){
		List<TbItem> tbItem = cartService.getTbItem(request);
		model.addAttribute("cartList", tbItem);
		return "cart";
	}
	
	@RequestMapping(value="/update/num/{itemid}/{num}",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult updateCart(HttpServletRequest request,HttpServletResponse response,@PathVariable Long itemid,@PathVariable Integer num){
		TaotaoResult result = cartService.updateCart(request, response, itemid, num);
		return result;
	}
	
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(HttpServletRequest request,HttpServletResponse response,@PathVariable Long itemId){
		cartService.deleteCart(request, response, itemId);
		return "redirect:/cart/cart.html";
	}

}
