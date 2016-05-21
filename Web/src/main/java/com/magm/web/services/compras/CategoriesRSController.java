package com.magm.web.services.compras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magm.compras.business.service.ICategoryService;
import com.magm.compras.model.Category;
import com.magm.core.bussiness.service.SimpleResponse;
import com.magm.core.bussiness.service.exception.ServiceException;
import com.magm.core.exception.NotFoundException;
import com.magm.web.services.Constants;

@RestController
@RequestMapping(value = Constants.URL_CATEGORIES)
public class CategoriesRSController {
	private static Logger LOG = LoggerFactory.getLogger(CategoriesRSController.class);
	
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> list(@RequestParam(value="description", defaultValue="") String q) {
		try {
			return new ResponseEntity<Object>(categoryService.list(q), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/{idCategory}/products", method = RequestMethod.GET)
	public ResponseEntity<Object> list(@PathVariable int idCategory) {
		try {
			return new ResponseEntity<Object>(categoryService.listProducts(idCategory), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Category Category) {
		try {
			System.out.println(Category);
			return new ResponseEntity<Object>(categoryService.save(Category), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@RequestBody Category Category) {
		try {
			return new ResponseEntity<Object>(categoryService.update(Category), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable int id) {
		try {
			Category c = new Category();
			c.setId(id);
			categoryService.delete(c);
			return ResponseEntity.ok().body(null);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable int id) {
		try {
			Category c = categoryService.load(id);
			return new ResponseEntity<Object>(c, HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(-1, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
}
