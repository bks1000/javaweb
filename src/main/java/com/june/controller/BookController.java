package com.june.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.june.core.utils.PageUtils;
import com.june.dto.Book;
import com.june.service.IBookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private IBookService service;
	
	@RequestMapping("/list")
	public ModelAndView getBookList() {
		ModelAndView mav = new ModelAndView("booklist");
		List<Book> lst = service.getBookList();
		mav.addObject("books", lst);
		return mav;
	}
	
	@RequestMapping("/add")
	public String addBook() {
		return "bookadd";
	}
	
	@RequestMapping("/save")
	public void save(@ModelAttribute("book")Book bean,HttpServletRequest request) {
		Map<String, Object> params = PageUtils.getParameters(request);
		Object object = params;
	}
}
