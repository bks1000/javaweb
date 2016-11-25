package com.june.dao;

import java.util.List;

import com.june.dto.Book;

public interface IBookDao {
	
	/**
	 * 查询图书列表
	 * @return
	 */
	List<Book> getBookList();
}
