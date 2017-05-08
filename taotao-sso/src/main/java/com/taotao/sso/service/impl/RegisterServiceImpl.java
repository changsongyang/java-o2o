package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.po.TbUser;
import com.taotao.po.TbUserExample;
import com.taotao.po.TbUserExample.Criteria;
import com.taotao.sso.service.RegisterService;

/**
 * 检查数据是否可用 用户注册
 * 
 * @author hulei
 *
 */
@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	TbUserMapper userMapper;

	@Override
	public TaotaoResult checkInfo(String param, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria create = example.createCriteria();
		// type为类型，可选参数1、2、3分别代表username、phone、email
		if (type == 1) {
			create.andUsernameEqualTo(param);
		} else if (type == 2) {
			create.andPhoneEqualTo(param);
		} else if (type == 3) {
			create.andEmailEqualTo(param);
		}
		List<TbUser> list =  userMapper.selectByExample(example);
		if(list == null || list.isEmpty()){
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult register(TbUser user) {
		if(StringUtils.isBlank(user.getUsername())){
			return TaotaoResult.build(400, "用户名不能为空");
		}
		if(StringUtils.isBlank(user.getPassword())){
			return TaotaoResult.build(400, "密码不能为空");
		}
		//重复检验
		if(user.getUsername() != null){
			if(!(boolean) checkInfo(user.getUsername(), 1).getData()){
				return TaotaoResult.build(400, "用户名已存");
			}	
		}
		if(user.getPhone() != null){
			if(!(boolean) checkInfo(user.getPhone(), 2).getData()){
				return TaotaoResult.build(400, "手机号码已存在");
			}	
		}
		if(user.getEmail() !=null){
			if(!(boolean) checkInfo(user.getEmail(), 3).getData()){
				return TaotaoResult.build(400, "邮箱已存在");
			}
		}
		
		user.setCreated(new Date());
		user.setUpdated(new Date());
		//md5加密
		String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(password);
		//入库
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

}
