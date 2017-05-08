package com.taotao.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbUser;
import com.taotao.sso.rest.redis.JedisClient;
import com.taotao.sso.service.TokenService;

/**
 * Token查询用户
 * 删除Token
 * @author hulei
 *
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	JedisClient jedisClient;
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	@Value("${REDIS_SESSION_EXPIRE}")
	private Integer REDIS_SESSION_EXPIRE;

	@Override
	public TaotaoResult geteToken(String token) {
		// 查询用户信息
		String json = jedisClient.get(REDIS_SESSION_KEY + ":" + token);
		// 判断是否过期
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(201, "Session已经过期");
		}
		// 将json转为java对象
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		// 更新过期时间
		jedisClient.expire(REDIS_SESSION_KEY + ":" + token, REDIS_SESSION_EXPIRE);
		return TaotaoResult.ok(user);
	}

	@Override
	public TaotaoResult deleteToken(String token) {
		try {
			jedisClient.del(REDIS_SESSION_KEY + ":" + token); 
			return TaotaoResult.ok("退出登录");
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}

}
