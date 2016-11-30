package com.june.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.june.dao.IBookDao;
import com.june.dto.Book;
import com.june.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookDao dao;
	
	public List<Book> getBookList() {
		return dao.getBookList();
	}

	public Book getBookById(int id) {
		return dao.getBookById(id);
	}

	public void saveBook(Book book) {
		dao.saveBook(book);
		
	}

	public void delBookById(int id) {
		dao.delBookById(id);
	}

}
