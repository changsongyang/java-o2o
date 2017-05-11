package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.order.pojo.OrderList;
import com.taotao.po.TbItem;
import com.taotao.po.TbUser;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.CreateOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	CartService cartService;
	@Autowired
	CreateOrderService createOrderService;
	
	@RequestMapping("/order-cart")
	public String orderCart(HttpServletRequest request,Model model){
		List<TbItem> list = cartService.getTbItem(request);
		model.addAttribute("cartList", list);
		return "order-cart";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String success(OrderList list,Model model,HttpServletRequest request,HttpServletResponse response){
		TbUser user = (TbUser) request.getAttribute("user");
		list.setUserId(user.getId());
		list.setBuyerNick(user.getUsername());
		TaotaoResult result = createOrderService.createOrder(list);
		model.addAttribute("user", user);
		model.addAttribute("orderId", result.getData());
		model.addAttribute("payment", list.getPayment());
		model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
		// 删除Cookie
		CookieUtils.deleteCookie(request, response, "TT_CART");
		return "success";
	}
	
	@RequestMapping("/myOrderCart")
	public String myOrderCart(){
		return "my-orders";
	}
	

}
