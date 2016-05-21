package com.magm.web.services.compras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magm.compras.business.service.ICategoryService;
import com.magm.web.services.Constants;

@RestController
@RequestMapping(value = Constants.URL_CATEGORIES)
public class CategoriesRSController {
	private static Logger LOG = LoggerFactory.getLogger(CategoriesRSController.class);
	
	@Autowired
	private ICategoryService categoryService;
	
	
}
