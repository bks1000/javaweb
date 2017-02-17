package com.june.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.june.core.utils.PageUtils;
import com.june.dto.BookType;
import com.june.service.IBookTypeService;

@Controller
@RequestMapping("/type")
public class BookTypeController {

	@Autowired
	private IBookTypeService service;
	
	@RequestMapping("/addbooktype")
	public String addBookType()
	{
		return "booktype/booktypeadd";
	}
	
	@RequestMapping("/update")
	public ModelAndView updateBookType(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("booktype/booktypeadd");
		int id = Integer.parseInt(PageUtils.getString(PageUtils.getParameters(request).get("id")));
		mav.addObject("bt", service.getBookTypeById(id));
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request)
	{
		Map<String, Object> data = PageUtils.getParameters(request);
		String id = PageUtils.getString(data.get("id"));
		String name = PageUtils.getString(data.get("name"));
		String parentid = PageUtils.getString(data.get("pid"));
		
		BookType dto = new BookType();
		if (!id.isEmpty()) {
			dto.setId(Integer.parseInt(id));
		}
		dto.setTypeName(name);
		dto.setParentId(Integer.parseInt(parentid));
		
		service.save(dto);
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}
	
	//http://localhost:8080/web/type/getbooktype.do
	@RequestMapping("/getbooktypelist")
	public ModelAndView getBookTypeList() {
		ModelAndView mav = new ModelAndView("booktype/booktypelist2");
		List<BookType> lst = service.getBookTypeList();
		mav.addObject("types", lst);
		return mav;
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
	
	@RequestMapping("/del")
	@ResponseBody
	public Map<String, Object> delBookType(HttpServletRequest request) {
		Map<String, Object> data = PageUtils.getParameters(request);
		int id = Integer.parseInt(PageUtils.getString(data.get("id")));
		service.delBookTypeById(id);
		
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("code", 0);
		return ret;
	}
}
