package com.taotao.sso.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.po.TbUser;
import com.taotao.po.TbUserExample;
import com.taotao.po.TbUserExample.Criteria;
import com.taotao.sso.rest.redis.JedisClient;
import com.taotao.sso.service.LoginService;

/**
 * 用户注册 token
 * 
 * @author hulei
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	TbUserMapper userMapper;
	@Autowired
	JedisClient jedisClient;
	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;
	@Value("${REDIS_SESSION_EXPIRE}")
	private Integer REDIS_SESSION_EXPIRE;
	private static String TT_TOKEN="TT_TOKEN";
	@Override
	public TaotaoResult login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isBlank(username)) {
			return TaotaoResult.build(400, "用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			return TaotaoResult.build(400, "密码不能为空");
		}
		TbUserExample example = new TbUserExample();
		Criteria create = example.createCriteria();
		create.andUsernameEqualTo(username);
		// 查询用户信息
		List<TbUser> list = userMapper.selectByExample(example);
		// 验证用户
		if (list == null || list.isEmpty()) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		TbUser user = list.get(0);
		// 验证密码
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		// 生成token
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		// 清空密码
		user.setPassword(null);
		// 存入redis
		jedisClient.set(REDIS_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
		// 设置过期时间
		jedisClient.expire(REDIS_SESSION_KEY + ":" + token, REDIS_SESSION_EXPIRE);
		// 把token写入cookie
		CookieUtils.setCookie(request, response, TT_TOKEN, token);
		// 返回token
		return TaotaoResult.ok(token);
	}

}
