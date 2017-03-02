package com.june.core.annotation;

import com.june.core.interceptor.MyAnnotationInterceptor;

import java.lang.annotation.*;

/**
 * 自定义注解，可用在类及方法上
 * 配合拦截器实现拦截效果
 * Created by lenovo on 2017/3/2.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface Before {
    Class<? extends MyAnnotationInterceptor> value();
}
