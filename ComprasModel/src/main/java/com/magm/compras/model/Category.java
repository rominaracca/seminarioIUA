package com.magm.compras.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
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
	
	private Set<Product> products = new HashSet<Product>(0);
	
	
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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "categories")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
