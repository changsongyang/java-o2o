package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.po.TbItem;
import com.taotao.po.TbItemDesc;
import com.taotao.po.TbItemParamItem;
import com.taotao.po.TbItemParamItemExample;
import com.taotao.po.TbItemParamItemExample.Criteria;
import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.ItemService;

/**
 * 商品详情 商品描述 商品规格参数
 * 
 * @author hulei
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	TbItemParamItemMapper tbItemParamItemMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_LIST}")
	private String REDIS_ITEM_LIST;
	@Value("${REDIS_ITEM_DES}")
	private String REDIS_ITEM_DES;
	@Value("${REDIS_ITEM_SPRC}")
	private String REDIS_ITEM_SPRC;
	@Value("${REDIS_TAOTAO_ITME_EXPIRE}")
	private Integer REDIS_TAOTAO_ITME_EXPIRE;

	@Override
	public TbItem getItemById(Long itemId) {
		try {
			// 读取缓存
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + REDIS_ITEM_LIST + ":" + itemId);
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象返回
				TbItem result = JsonUtils.jsonToPojo(json, TbItem.class);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 根据商品id查询商品基本信息
		TbItem result = tbItemMapper.selectByPrimaryKey(itemId);
		try {
			// 商品基本信息加入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + REDIS_ITEM_LIST + ":" + itemId, JsonUtils.objectToJson(result));
			// 设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + REDIS_ITEM_LIST + ":" + itemId, REDIS_TAOTAO_ITME_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public TbItemDesc getItemDesc(Long itemId) {
		try {
			// 读取缓存
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + REDIS_ITEM_DES + ":" + itemId);
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象返回
				TbItemDesc result = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 商品描述查询
		TbItemDesc result = tbItemDescMapper.selectByPrimaryKey(itemId);
		try {
			// 商品描述加入缓存
			jedisClient.set(REDIS_ITEM_KEY + ":" + REDIS_ITEM_DES + ":" + itemId, JsonUtils.objectToJson(result));
			// 设置过期时间
			jedisClient.expire(REDIS_ITEM_KEY + ":" + REDIS_ITEM_DES + ":" + itemId, REDIS_TAOTAO_ITME_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public TbItemParamItem getItemParamById(Long itemId) {
		try {
			// 读取缓存
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + REDIS_ITEM_SPRC + ":" + itemId);
			if (StringUtils.isNotBlank(json)) {
				// 把json数据转换成java对象返回
				TbItemParamItem result = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0) {
			TbItemParamItem result = list.get(0);
			try {
				// 商品规格参数加入缓存
				jedisClient.set(REDIS_ITEM_KEY + ":" + REDIS_ITEM_SPRC + ":" + itemId, JsonUtils.objectToJson(result));
				// 设置过期时间
				jedisClient.expire(REDIS_ITEM_KEY + ":" + REDIS_ITEM_SPRC + ":" + itemId, REDIS_TAOTAO_ITME_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		return null;
	}

}
