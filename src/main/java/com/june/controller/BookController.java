package com.june.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.june.core.utils.PageUtils;
import com.june.core.utils.RedisUtil;
import com.june.dto.Book;
import com.june.service.IBookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private IBookService service;
	
	//测试jedis
	//@Resource(name="redisUtil")
	//private RedisUtil redisUtil;
	
	@RequestMapping("/list")
	public ModelAndView getBookList(HttpServletRequest request) {
		
		//redisUtil.saveObject(request.getSession().getId().getBytes(), "book/list.do".getBytes());
		
		ModelAndView mav = new ModelAndView("book/booklist");
		List<Book> lst = service.getBookList();
		mav.addObject("books", lst);
		return mav;
	}
	
	@RequestMapping("/add")
	public String addBook(ModelMap bt) {
		
		bt.addAttribute("bt", new Book());
		return "book/bookadd";
	}
	
	@RequestMapping("/update")
	public ModelAndView updateBook(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("book/bookadd");
		int id = Integer.parseInt(PageUtils.getString(PageUtils.getParameters(request).get("id")));
		mav.addObject("bt", service.getBookById(id));
		return mav;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(@ModelAttribute("form")Book book,HttpServletRequest request) {
		//Map<String, Object> params = PageUtils.getParameters(request);
		service.saveBook(book);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 1);
		return map;
	}
}
