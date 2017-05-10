package com.taotao.portal.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.order.pojo.OrderList;

public interface CreateOrderService {
	 TaotaoResult createOrder(OrderList list);
}
