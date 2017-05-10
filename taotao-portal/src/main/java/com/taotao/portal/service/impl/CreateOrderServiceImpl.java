package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.order.pojo.OrderList;
import com.taotao.portal.service.CreateOrderService;

/**
 * 请求webservice生成订单
 * 
 * @author hulei
 *
 */
@Service
public class CreateOrderServiceImpl implements CreateOrderService {

	@Value("${ORDER_URL}")
	private String ORDER_URL;
	@Value("${CREATE_ORDER_URL}")
	private String CREATE_ORDER_URL;

	@Override
	public TaotaoResult createOrder(OrderList list) {
		String json = JsonUtils.objectToJson(list);
		String order = HttpClientUtil.doPostJson(ORDER_URL + CREATE_ORDER_URL, json);
		// 把string转换成TaotaoResult对象
		TaotaoResult result = TaotaoResult.format(order);
		return result;
	}

}
