package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.rest.redis.JedisClient;
import com.taotao.rest.service.SynccontentService;
/**
 * 同步redis
 * @author hulei
 *
 */
@Service
public class SynccontentServiceImpl implements SynccontentService {
	
	@Autowired
	JedisClient jedisClient;
	
	@Value("${REDIS_CONTENT_KEY}")
	String REDIS_CONTENT_KEY;
	
	@Override
	public TaotaoResult syncContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid + "");
		return TaotaoResult.ok();
	}

}
