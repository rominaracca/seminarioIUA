package com.magm.compras.model;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
public class ProductTest {

	@Test
	public void indentity() {
		Product p1=new Product();
		p1.setId(1);
		p1.setDescription("Product X");
		
		Product p2=new Product();
		p2.setId(2);
		p2.setDescription("Product X");
		
		assertNotEquals("Product.equal() fail!",  p1, p2);
		p2.setId(1);
		assertEquals("Product.equal() fail!",  p1, p2);
	}
	
	
	 public static final String EXPECTED_DESCRIPTION = "Product Y";
	 public static final double EXPECTED_PRICE = 50.20;
	 public static final String EXPECTED_CODE = "PRY001";
	 public static final int id = 20;
	 private Category category; 
	 private Product product;

	    @Before
	    public void setUp() throws Exception {
	    	category = new Category();
		 	category.setId(42);
		    category.setDescription("Mueble");
		 	product = new Product();
		 	product.setId(20);
		 	product.setDescription("Product Y");
		 	product.setPrice(50.20);
		 	product.setCode("PRY001");
			product.setCategory(category);
		 	
	    }

	    @After
	    public void tearDown() throws Exception {
	        System.out.println("Product Test Completed");
	    }

	    @Test
	    public void testCategory() throws Exception {
	        Assert.assertEquals(EXPECTED_DESCRIPTION, product.getDescription());
	        Assert.assertEquals(EXPECTED_PRICE, product.getPrice(), 0);
	        Assert.assertEquals(EXPECTED_CODE, product.getCode());
	        Assert.assertEquals(category, product.getCategory());
	        Assert.assertEquals(id, product.getId());
	    }
}
