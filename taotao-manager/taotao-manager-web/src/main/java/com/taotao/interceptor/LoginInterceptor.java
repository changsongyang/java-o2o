package com.taotao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/** 
 * 登录认证的拦截器 
 */ 
public class LoginInterceptor implements HandlerInterceptor{
	
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
		//取session
		String name = (String) request.getSession().getAttribute("user");
        if(StringUtils.isBlank(name)){
        	response.sendRedirect("http://localhost:9999/login");
        	return false;
        }
       //设置session
       HttpSession session = request.getSession();
       session.setAttribute("user",name);
       //更新过期时间
       session.setMaxInactiveInterval(30*60);
		return true;
	}
}
