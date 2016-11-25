package com.june.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book",schema="book")
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bookid")
	private int bookId;
	
	@Column(name="bookname",length=200)
	private String bookName;
	
	@Column(name="author",length=200)
	private String author;
	
	@Column(name="oldname",length=200)
	private String oldName;
	
	@Column(name="translator",length=200)
	private String translator;
	
	@Column(name="publishing",length=50)
	private String publishing;
	
	@Column(name="papers")
	private int papers;
	
	@Column(name="money",precision=7, scale=2)
	private BigDecimal money;
	
	@Column(name="isbn",length=30)
	private String isbn;

	/**
	 * @return bookId
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId 要设置的 bookId
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return bookName
	 */
	public String getBookName() {
		return bookName;
	}

	/**
	 * @param bookName 要设置的 bookName
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/**
	 * @return author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author 要设置的 author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return oldName
	 */
	public String getOldName() {
		return oldName;
	}

	/**
	 * @param oldName 要设置的 oldName
	 */
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	/**
	 * @return translator
	 */
	public String getTranslator() {
		return translator;
	}

	/**
	 * @param translator 要设置的 translator
	 */
	public void setTranslator(String translator) {
		this.translator = translator;
	}

	/**
	 * @return publishing
	 */
	public String getPublishing() {
		return publishing;
	}

	/**
	 * @param publishing 要设置的 publishing
	 */
	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	/**
	 * @return papers
	 */
	public int getPapers() {
		return papers;
	}

	/**
	 * @param papers 要设置的 papers
	 */
	public void setPapers(int papers) {
		this.papers = papers;
	}

	/**
	 * @return money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money 要设置的 money
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @return isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn 要设置的 isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
