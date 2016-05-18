package com.magm.compras.model;
import org.junit.Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

public class CategoryTest {
	
    public static final String EXPECTED_DESCRIPTION = "Mueble";
    public static final int id = 42;
    private Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
        category.setId(42);
        category.setDescription("Mueble");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Category Test Completed");
    }

    @Test
    public void testCategory() throws Exception {
        Assert.assertEquals(EXPECTED_DESCRIPTION, category.getDescription());
        Assert.assertEquals(id, category.getId());
    }
}
