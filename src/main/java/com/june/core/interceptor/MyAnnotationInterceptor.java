package com.june.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 采用注解方式定义的拦截器，处理程序接口
 * 需要搭配注解，以及在mvc:interceptor配置中的拦截器调用
 * Created by lenovo on 2017/3/2.
 */
public interface MyAnnotationInterceptor {
    /**
     * 拦截处理接口
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    boolean interceptor(HttpServletRequest request, HttpServletResponse response,Object handler) throws  Exception;
}
