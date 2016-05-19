package com.magm.compras.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magm.compras.model.Category;
import com.magm.compras.model.Product;
import com.magm.compras.persistence.ICategoryDAO;
import com.magm.core.persistence.dao.hibernate.GenericDAO;
import com.magm.core.persistence.exception.PersistenceException;

public class CategoryDAO extends GenericDAO<Category, Integer> implements ICategoryDAO {

	private static Logger LOG = LoggerFactory.getLogger(ProductDAO.class);
	
	public CategoryDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> list(int idCategory) throws PersistenceException {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		try {
			products =  getSession()
					.createCriteria(getDomainClass())
							.add(Restrictions.eq("category.id", idCategory))
					.list();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new PersistenceException(e.getMessage(), e);
		}
		finally {
			closeSession();
		}
		return products;
	}
}
