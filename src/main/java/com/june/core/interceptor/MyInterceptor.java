package com.june.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.june.core.annotation.Before;
import com.june.core.utils.Const;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义拦截器(访问日志记录)
 * @author lenovo
 *
 */
public class MyInterceptor implements HandlerInterceptor {

	//在Action执行之前执行
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean handlerResult = true;
		if (StringUtils.isEmpty(Const.PROJECT_PATH)){
			Const.PROJECT_PATH = request.getContextPath();
		}
		
		request.setAttribute("base", Const.PROJECT_PATH);
		request.setAttribute("ctx", Const.PROJECT_PATH);
		
		//查找处理类或处理方法是否标记了Before注解；如果标记了，调用处理程序
		if (handler!=null){
            List<Annotation> lst = new ArrayList<Annotation>();
            if (handler.getClass().isAssignableFrom(HandlerMethod.class)){
                Class clazz = ((HandlerMethod)handler).getMethod().getDeclaringClass();
                Annotation before = clazz.getAnnotation(Before.class);
                if (before!=null){
                	//处理类级注解，加入数组，统一处理
					lst.add(before);
				}
				before = ((HandlerMethod)handler).getMethod().getAnnotation(Before.class);
                if (before!=null){
					//处理方法级注解，加入数组，统一处理
					lst.add(before);
				}
            }
			for (Annotation anno:lst) {
				Class<? extends MyAnnotationInterceptor> myAnnotationInterceptor= ((Before)anno).value();
				Object object = Class.forName(myAnnotationInterceptor.getCanonicalName()).newInstance();
				Class[] params = new Class[]{HttpServletRequest.class,HttpServletResponse.class,Object.class};
				Method interceptor = object.getClass().getMethod("interceptor",params);
				handlerResult = Boolean.parseBoolean(interceptor.invoke(object,new Object[]{request,response,handler}).toString());
				if (!handlerResult){
					return  handlerResult;//返回false直接退出
				}
			}
		}
		return handlerResult;//继续执行
	}

	//在Action执行之后，生成视图之前执行
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		//HandlerMethod handlerMethod = (HandlerMethod) handler;
		//params.put("invokedmethod", handlerMethod.getMethod().getName()); 
		//params.put("parametervalues", JsonUtils.mapToJson(PageUtils.getParameters(request)));
		//String ip = getIpAddr(request);

		//设置FREEMARK公用标签常量
		//modelAndView.addObject("base",request.getContextPath());
		//设置jsp页面公用标签常量
		//modelAndView.addObject("ctx",request.getContextPath());
	}
	
	//最后执行(释放资源或处理异常)
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//TODO:异常处理
		Exception exception = ex;
		//TODO:查找处理类或处理方法是否标记了After注解；如果标记了，调用处理程序
	}

}
