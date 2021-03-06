package com.magm.compras.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "categories")	
@Proxy(lazy=false)
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7006231512576144664L;
	
	@Id
	@GeneratedValue
	@Column(name="idCategory")
	private int id;
	
	@Column
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy="category")
	private Set<Product> products;
	
	
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
	
	@Override
	public String toString() {
		return String.format("Category: [%d] %s", getId(), getDescription());
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Category) obj).getId() == getId();
	}
	
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
