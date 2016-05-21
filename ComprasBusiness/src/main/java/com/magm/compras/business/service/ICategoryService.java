package com.magm.compras.business.service;

import java.util.List;

import com.magm.compras.model.Category;
import com.magm.compras.model.Product;
import com.magm.core.bussiness.service.IGenericService;
import com.magm.core.bussiness.service.exception.ServiceException;

public interface ICategoryService extends IGenericService<Category, Integer>{

	public List<Product> listProducts(int idCategory) throws ServiceException;
	public List<Category> list(String part) throws ServiceException;

}
