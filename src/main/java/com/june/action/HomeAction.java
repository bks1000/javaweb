package com.june.action;

import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by lenovo on 2017/2/3.
 * struts2配置注解没有成功
 * 说明：struts的拦截是类级的，springmvc的拦截是方法及的
 */
public class HomeAction extends ActionSupport {

    @Action(value = "/main")
    public String execute() throws Exception {
        return "master";
    }
}
