package com.june.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="book",schema="book")
public class Book implements Serializable {

}
