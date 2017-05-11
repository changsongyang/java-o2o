package com.taotao.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.po.TbUser;
import com.taotao.po.TbUserExample;
import com.taotao.po.TbUserExample.Criteria;
import com.taotao.service.LoginService;

/**
 * 用户登录
 * 
 * @author hulei
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	TbUserMapper tbUser;

	@Override
	public TaotaoResult login(HttpServletRequest request, HttpServletResponse response, String username,
			String password) {
		if (StringUtils.isBlank(username)) {
			return TaotaoResult.build(400, "用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			return TaotaoResult.build(400, "密码不能为空");
		}
		TbUserExample example = new TbUserExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUsernameEqualTo(username);
		List<TbUser> list = tbUser.selectByExample(example);
		if (list == null || list.isEmpty()) {
			return TaotaoResult.build(400, "用户名或者密码错误");
		}
		TbUser user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "用户名或密码错误");
		}
		// 设置session
		HttpSession session = request.getSession();
		session.setAttribute("user", user.getUsername());
		//过期时间
		session.setMaxInactiveInterval(30 * 60);
		return TaotaoResult.ok("登录成功");
	}

}
