package com.june.service;

import java.util.List;

import com.june.dto.Book;

public interface IBookService {

	/**
	 * 查询图书列表
	 * @return
	 */
	List<Book> getBookList();
}
