package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.po.TbUser;
import com.taotao.portal.service.LoginService;
/** 
 * 登录认证的拦截器 
 */ 
public class LoginInterceptor implements HandlerInterceptor{
	@Autowired
	LoginService loginService;
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	@Value("${SSO_LOGIN}")
	private String SSO_LOGIN;
    /** 
     * Handler执行完成之后调用这个方法 
     */  
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	 /** 
     * Handler执行之后，ModelAndView返回之前调用这个方法 
     */  
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse  response, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/** 
     * Handler执行之前调用这个方法 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//从cookie中取TT_TOKEN
		String TOKEN = CookieUtils.getCookieValue(request, "TT_TOKEN");
		getUrl(request);
		//判断用户是否登录
		if(StringUtils.isBlank(TOKEN)){
			//跳转登录页面,带入当前URL
			response.sendRedirect(SSO_BASE_URL + SSO_LOGIN + "?redirect=" + getUrl(request));
			return false;
		}else{
			TbUser user = loginService.getUserByToken(TOKEN);
			//判断TOKEN是否过期
			if(null == user){
				response.sendRedirect(SSO_BASE_URL + SSO_LOGIN + "?redirect=" + getUrl(request));
				return false;
			}
		}
		return true;
	}
	private String getUrl(HttpServletRequest request) {
		String url = request.getScheme() + "://" 
				+ request.getServerName() + ":" 
				+ request.getServerPort() 
				+ request.getContextPath() 
				+ request.getRequestURI(); 
		//String url2 = request.getRequestURL().toString();
		//System.out.println(url);
		//System.out.println(url2);
		return url;
	}
}
