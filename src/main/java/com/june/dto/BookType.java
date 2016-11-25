/**
 * 
 */
package com.june.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 图书类型DTO
 * @author lenovo
 *
 */
@Entity
@Table(name="booktype",schema="book")
public class BookType implements Serializable {
	/**
	 * 类型ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	/**
	 * 类型名称
	 */
	@Column(name = "typename", length = 50)
	private String typeName;
	/**
	 * 上级类型ID
	 */
	@Column(name = "parentid")
	private int parentId;
	
	/**
	 * @return typeId
	 */
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")*/
	public int getId() {
		return id;
	}
	/**
	 * @param typeId 要设置的 typeId
	 */
	public void setId(int typeId) {
		this.id = typeId;
	}
	/**
	 * @return typeName
	 */
	//映射表中name这个字段 ，长度是50
	//@Column(name = "typename", length = 50)
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName 要设置的 typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return parentId
	 */
	//映射表中name这个字段 ，长度是500
	//@Column(name = "parentid")
	public int getParentId() {
		return parentId;
	}
	/**
	 * @param parentId 要设置的 parentId
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
