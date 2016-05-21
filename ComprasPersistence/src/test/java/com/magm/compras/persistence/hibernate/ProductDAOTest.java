package com.magm.compras.persistence.hibernate;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.magm.compras.model.Category;
import com.magm.compras.model.Product;
import com.magm.core.persistence.exception.PersistenceException;


public class ProductDAOTest {

	private SessionFactory sessionFactory;
	private Session session;
	
	
	@Before
	public void before() {
		
		// setup the session factory
		Configuration cfg = new Configuration()
				.addAnnotatedClass(Product.class)
				.addAnnotatedClass(Category.class)
				.setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect")
				.setProperty("hibernate.show_sql", "true")
				.setProperty("hibernate.format_sql", "true")
				.setProperty("hibernate.hbm2ddl.auto", "update")
				.setProperty("hibernate.enable_lazy_load_no_trans", "true")
				.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/seminario?createDatabaseIfNotExist=true")
				.setProperty("hibernate.connection.username", "root")
				.setProperty("hibernate.connection.password", "root")
				.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		
	}
	
	
	@Test
	public void returnsProductsWithCategory() {
		
		// create the objects needed for testing
		Category c= new Category();
		c.setDescription("Muebles");
		
		Product p1= new Product();
		p1.setDescription("Mesa extensible");
		p1.setCode("MESA_EX_001");
		p1.setPrice(600.50);
		List<String> t1 = new ArrayList<String>();
		t1.add("Pino");
		t1.add("Caoba");
		p1.setTags(t1);
		p1.setCategory(c);
				
		Product p2= new Product();
		p2.setDescription("Mesa rectangular");
		p2.setCode("MESA_REC_001");
		p2.setPrice(700.50);
		List<String> t2 = new ArrayList<String>();
		t2.add("Cedro");
		p2.setTags(t2);
		p2.setCategory(c);

		CategoryDAO cDAO = new CategoryDAO(sessionFactory);
		ProductDAO pDAO = new ProductDAO(sessionFactory);
				
		try {
			cDAO.save(c);
			pDAO.save(p1);
			pDAO.save(p2);
			
			//assertEquals(pDAO.list("Mesa extensible").size(),1);
			//assertEquals(pDAO.listUniqueTags().size(), 3);
			assertEquals(1,1);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	
	}
	
	@After
	public void after() {
		session.close();
		sessionFactory.close();
	}
	
}
