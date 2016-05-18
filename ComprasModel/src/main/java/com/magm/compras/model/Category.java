package com.magm.compras.model;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name = "categories")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7006231512576144664L;
	
	@Id
	@GeneratedValue
	private int id;
	
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
