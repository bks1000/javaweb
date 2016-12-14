package com.june.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器(访问日志记录)
 * @author lenovo
 *
 */
public class MyInterceptor implements HandlerInterceptor {

	//在Action执行之前执行
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		return true;//继续执行
	}

	//在Action执行之后，生成视图之前执行
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {


		HandlerMethod handlerMethod = (HandlerMethod) handler;  
		//params.put("invokedmethod", handlerMethod.getMethod().getName()); 
		//params.put("parametervalues", JsonUtils.mapToJson(PageUtils.getParameters(request)));
		//String ip = getIpAddr(request);
	}
	
	//最后执行(释放资源或处理异常)
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Exception exception = ex;

	}

}
