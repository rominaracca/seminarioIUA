package com.magm.compras.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
@Access(value = AccessType.FIELD)
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = -2237359968314973968L;

	@Id
	@GeneratedValue
	private int id;
	
	private String description;
	private double price;
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "idCategory")
	private Category category;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return ((Product) obj).getId() == getId();
	}
	
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public String toString() {
		return String.format("Product: [%d] %s %s", getId(), getDescription(), getPrice());
	}
	

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "productTags", joinColumns = @JoinColumn(name = "idProduct") )
	@Column(name = "tag")
	private List<String> tags = new ArrayList<String>();

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public Category getCategory(){
		return category;
	} 

	public void setCategory(Category categoty) {
		this.category = categoty;
	}
}
