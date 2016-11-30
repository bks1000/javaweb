package com.june.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
