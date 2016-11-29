package com.june.dao;

import java.util.List;

import com.june.dto.BookType;

public interface IBookTypeDao {
	/**
	 * 保存
	 * @param book
	 * @return
	 */
	void save(BookType book);
	
	/**
	 * 根据ID 获取实体
	 * @param id
	 * @return
	 */
	BookType getBookTypeById(int id);
	
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
	
	/**
	 * 删除
	 * @param id
	 */
	void delBookTypeById(int id);
}
