package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.po.TbContent;
import com.taotao.po.TbContentExample;
import com.taotao.po.TbContentExample.Criteria;
import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.ContentService;

/**
 * 广告位
 * 
 * @author hulei
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	TbContentMapper tbContentMapper;
	@Autowired
	JedisClient jedisClient;
	@Value("${REDIS_CONTENT_KEY}")
	String REDIS_CONTENT_KEY;

	@Override
	public TaotaoResult getContentList(Long categoryId) {
		
		try {
			String hget = jedisClient.hget(REDIS_CONTENT_KEY, categoryId + "");
			if(!StringUtils.isBlank(hget)){
				List<TbContent> list = JsonUtils.jsonToList(hget, TbContent.class);
				return TaotaoResult.ok(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<TbContent> list = null;
		try {
			TbContentExample example = new TbContentExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andCategoryIdEqualTo(categoryId);
			list = tbContentMapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		//存入redis
		try {
			String objectToJson = JsonUtils.objectToJson(list);
			jedisClient.hset(REDIS_CONTENT_KEY, categoryId + "", objectToJson);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return TaotaoResult.ok(list);
	}

}
