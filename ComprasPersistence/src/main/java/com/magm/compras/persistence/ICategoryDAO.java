package com.magm.compras.persistence;

import java.util.List;

import com.magm.compras.model.Category;
import com.magm.compras.model.Product;
import com.magm.core.persistence.dao.IGenericDAO;
import com.magm.core.persistence.exception.PersistenceException;

public interface ICategoryDAO extends IGenericDAO<Category, Integer> {
	
	public List<Product> listProducts(int idCategory) throws PersistenceException;
	public List<Category> list(String part) throws PersistenceException;

}
