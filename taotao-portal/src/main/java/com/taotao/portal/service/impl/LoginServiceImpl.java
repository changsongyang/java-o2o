package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.po.TbUser;
import com.taotao.portal.service.LoginService;
/**
 * token
 * @author hulei
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_TOKEN_USER_URL}")
	private String SSO_TOKEN_USER_URL;
	@Override
	public TbUser getUserByToken(String token) {
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_TOKEN_USER_URL + token);
			// 把json转换成java对象
			TaotaoResult formatToPojo = TaotaoResult.formatToPojo(json, TbUser.class);
			if(formatToPojo.getStatus() == 200){
				TbUser result = (TbUser) formatToPojo.getData();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
