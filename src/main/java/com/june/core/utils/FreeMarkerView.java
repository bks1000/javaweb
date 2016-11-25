package com.june.core.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FreeMarkerView extends 
	org.springframework.web.servlet.view.freemarker.FreeMarkerView {
	protected void exposeHelpers(Map<String, Object> model,
			HttpServletRequest request) throws Exception {
		model.put("ctx", request.getContextPath());
	}
}
