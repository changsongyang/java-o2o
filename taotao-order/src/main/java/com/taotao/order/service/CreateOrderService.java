package com.taotao.order.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.order.pojo.OrderList;

public interface CreateOrderService {
	 TaotaoResult createOrder(OrderList list);
}
