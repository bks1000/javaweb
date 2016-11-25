package com.june.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.june.dao.*;
import com.june.dto.BookType;

@Repository
public class BookTypeDaoImpl implements IBookTypeDao {

	//@Autowired 要求变量名和配置文件的ID设置相同
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//@Resource 如果设置name（配置文件中的bean id）值，则变量名可以和配置文件的ID不同
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public int save(BookType book) {
		sessionFactory.getCurrentSession().save(book);
		
		String sql = "insert INTO booktype(typename,parentid) VALUES(?,?)";
		return jdbcTemplate.update(sql, book.getTypeName(),book.getParentId());
	}
	
	
}
