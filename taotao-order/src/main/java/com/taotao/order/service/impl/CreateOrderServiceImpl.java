package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.pojo.OrderList;
import com.taotao.order.service.CreateOrderService;
import com.taotao.po.TbOrderItem;
import com.taotao.po.TbOrderShipping;
import com.taotao.rest.redis.JedisClient;

/**
 * 生成订单信息
 * 
 * @author hulei
 *
 */
@Service
public class CreateOrderServiceImpl implements CreateOrderService {

	@Autowired
	TbOrderMapper tbOrderMapper;
	@Autowired
	TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	TbOrderShippingMapper tbOrderShippingMapper;
	@Autowired
	JedisClient jedisClient;
	@Value("${REDIS_ORDER_ID_BEGIN}")
	private String REDIS_ORDER_ID_BEGIN;
	@Value("${REDIS_ORDER_ID_KEY}")
	private String REDIS_ORDER_ID_KEY;
	@Value("${REDIS_ORDER_ITEM_ID_KEY}")
	private String REDIS_ORDER_ITEM_ID_KEY;

	@Override
	public TaotaoResult createOrder(OrderList orderInfo) {

		// 获得新的订单号
		Long orderId = getOrderId();
		// 补全订单信息
		orderInfo.setOrderId(orderId.toString());
		// 支付类型，1、在线支付，2、货到付款
		orderInfo.setPaymentType(1);
		// 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		orderInfo.setStatus(1);
		Date date = new Date();
		orderInfo.setCreateTime(date);
		orderInfo.setUpdateTime(date);
		// 把订单信息插入到订单表
		tbOrderMapper.insert(orderInfo);
		
		//订单明细处理
		List<TbOrderItem> orderItems = orderInfo.getOrderItems();
		for (TbOrderItem tbOrderItem : orderItems) {
			//取订单明细编号
			long orderItemId = jedisClient.incr(REDIS_ORDER_ITEM_ID_KEY);
			tbOrderItem.setItemId(orderItemId + "");
			tbOrderItem.setOrderId(orderId.toString());
			//把订单明细插入
			tbOrderItemMapper.insert(tbOrderItem);
		}
		
		//物流信息处理
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId.toString());
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		tbOrderShippingMapper.insert(orderShipping);
		return TaotaoResult.ok(orderId);
	}

	private Long getOrderId() {
		// 获得一个订单号
		String oid = jedisClient.get(REDIS_ORDER_ID_KEY);
		if (StringUtils.isBlank(oid)) {
			jedisClient.set(REDIS_ORDER_ID_KEY, REDIS_ORDER_ID_BEGIN);
		}
		// 取订单号
		Long id = jedisClient.incr(REDIS_ORDER_ID_KEY);
		return id;
	}

}
