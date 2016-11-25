package com.june.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.june.dao.IBookTypeDao;
import com.june.dto.BookType;
import com.june.service.IBookTypeService;

@Service
public class BookTypeServiceImpl implements IBookTypeService {
	
	@Autowired
	private IBookTypeDao dao;

	public int save(BookType book) {
		return dao.save(book);
	}

}
