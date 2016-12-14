package com.june.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器，第二种实现方法，继承Adapter类
 * @author lenovo
 *
 */
public class MyInterceptor2 extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO 自动生成的方法存根
		return super.preHandle(request, response, handler);
	}
}
