package com.magm.compras.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magm.compras.business.service.ICategoryService;
import com.magm.compras.model.Category;
import com.magm.compras.model.Product;
import com.magm.compras.persistence.ICategoryDAO;
import com.magm.core.bussiness.service.exception.ServiceException;
import com.magm.core.bussiness.service.impl.GenericService;
import com.magm.core.persistence.exception.PersistenceException;

public class CategoryService extends GenericService<Category, Integer> implements ICategoryService {
	private static Logger LOG = LoggerFactory.getLogger(CategoryService.class);
	private ICategoryDAO categoryDAO;

	public CategoryService(ICategoryDAO categoryDAO) {
		super(categoryDAO);
		this.categoryDAO = categoryDAO;
	}

	@Override
	public List<Product> list(int idCategory) throws ServiceException {
		try {
			return categoryDAO.list(idCategory);
		} catch (PersistenceException e) {
			LOG.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage(), e);
		}
	}

}
