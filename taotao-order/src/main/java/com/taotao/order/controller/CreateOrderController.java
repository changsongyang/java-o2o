package com.taotao.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.order.pojo.OrderList;
import com.taotao.order.service.CreateOrderService;

@Controller
public class CreateOrderController {

	@Autowired
	CreateOrderService createOrderService;
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody OrderList order) {
		try {
			TaotaoResult result = createOrderService.createOrder(order);
			return result;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}

}
