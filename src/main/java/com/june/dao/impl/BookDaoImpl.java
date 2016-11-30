package com.june.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.june.core.utils.HibernateDao;
import com.june.dao.IBookDao;
import com.june.dto.Book;

@Repository
public class BookDaoImpl extends HibernateDao implements IBookDao {

	public List<Book> getBookList() {
		return this.find("from book");
	}

	public Book getBookById(int id) {
		return get(Book.class, id);
	}

	public void saveBook(Book book) {
		saveOrUpdate(book);
	}

	public void delBookById(int id) {
		executeSql("DELETE FROM book WHERE bookid=?", id);
	}

}
