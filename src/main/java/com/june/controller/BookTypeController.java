package com.june.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.june.core.utils.PageUtils;
import com.june.dto.BookType;
import com.june.service.IBookTypeService;

@Controller
@RequestMapping("/type")
public class BookTypeController {

	@Autowired
	private IBookTypeService service;
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request)
	{
		Map<String, Object> data = PageUtils.getParameters(request);
		String name = data.get("n").toString();
		int parentid=0;
		
		BookType dto = new BookType();
		dto.setTypeName(name);
		dto.setParentId(parentid);
		
		int result = service.save(dto);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		ret.put("result", result);
		return ret;
	}
	
	//http://localhost:8080/web/type/getbooktype.do
	@RequestMapping("/getbooktypelist")
	@ResponseBody
	public Map<String, Object> getBookTypeList() {
		List<BookType> lst = service.getBookTypeList();
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		ret.put("result", lst);
		return ret;
	}
	
	@RequestMapping("/getbooktypepage")
	@ResponseBody
	public Map<String, Object> getBookTypePage(HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		int index=Integer.parseInt(data.get("index").toString());
		int length=Integer.parseInt(data.get("size").toString());
		List<BookType> lst = service.queryForPage(index, length);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		ret.put("result", lst);
		return ret;
	}
	
}
