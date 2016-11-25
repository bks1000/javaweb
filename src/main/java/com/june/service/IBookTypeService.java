package com.june.service;

import java.util.List;

import com.june.dto.BookType;

public interface IBookTypeService {
	
	/**
	 * 保存
	 * @param book
	 * @return
	 */
	int save(BookType book);

	/**
	 * 查询图书类别列表
	 * @return
	 */
	List<BookType> getBookTypeList();
	
	/**
	 * 分页查询图书类别列表
	 * @param offset index
	 * @param length 条数
	 * @return
	 */
	List<BookType> queryForPage(int offset,int length);
}
