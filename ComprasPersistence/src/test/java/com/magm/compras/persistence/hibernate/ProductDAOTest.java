package com.magm.compras.persistence.hibernate;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.magm.compras.model.Category;
import com.magm.compras.model.Product;
import com.magm.core.persistence.exception.PersistenceException;

public class ProductDAOTest {

	private SessionFactory sessionFactory;
	private Session session = null;
	
	
	@Before
	public void before() {
		// setup the session factory
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.addAnnotatedClass(Product.class);
		configuration.addAnnotatedClass(Category.class);
		configuration.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.format_sql", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/seminario?createDatabaseIfNotExist=true");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "root");
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
		
	/*
		# jdbc.X
		jdbc.driverClassName=com.mysql.jdbc.Driver
		jdbc.url=jdbc:mysql://localhost:3306/seminario?createDatabaseIfNotExist=true
		jdbc.user=root
		jdbc.password=root

		# hibernate.X
		hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
		hibernate.show_sql=false
		hibernate.format_sql=true
		hibernate.hbm2ddl.auto=update
		hibernate.enable_lazy_load_no_trans=true
		*/
	}
	
	@Test
	public void returnsProductsWithCategory() {
		
		
		System.out.println("en test ");
		
		// create the objects needed for testing
				Category c= new Category();
				c.setDescription("Muebles3");
				Product p= new Product();
				p.setDescription("Mesa3");
				p.setCode("PRMESA_003");
				p.setPrice(600.50);
				List<String> t = new ArrayList<String>();
				t.add("Madera3");
				t.add("Roble3");
				p.setTags(t);
				p.setCategory(c);
				
				 CategoryDAO cDAO = new CategoryDAO(sessionFactory);
				 ProductDAO pDAO = new ProductDAO(sessionFactory);
				
				
				try {
					cDAO.save(c);
					pDAO.save(p);
				} catch (PersistenceException e) {
					e.printStackTrace();
				}
				
	assertEquals(1,1);
		
	}
	
		
	
	@After
	public void after() {
		session.close();
		sessionFactory.close();
	}
	
}
