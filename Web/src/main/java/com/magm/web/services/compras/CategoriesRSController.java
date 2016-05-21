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
	
	/**
	 * Obtener una lista de categorías:  http://localhost:8080/Web/api/v1/categories
	 * Se puede filtrar por query description: http://localhost:8080/Web/api/v1/products?description=foo
	 * @param q filtro
	 * @return JSON de lista de categories
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> list(@RequestParam(value="description", defaultValue="") String q) {
		try {
			return new ResponseEntity<Object>(categoryService.list(q), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Obtener una lista de productos por categoría:  http://localhost:8080/Web/api/v1/categories/{id}/products
	 * @param q filtro
	 * @return JSON de lista de productos
	 */
	@RequestMapping(value = "/{idCategory}/products", method = RequestMethod.GET)
	public ResponseEntity<Object> list(@PathVariable int idCategory) {
		try {
			return new ResponseEntity<Object>(categoryService.listProducts(idCategory), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Crear nueva categoria:  http://localhost:8080/Web/api/v1/categories
	 * @param product JSON para crear la categoría
	 * @return JSON de la categoría creada creado
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Category Category) {
		try {
			System.out.println(Category);
			return new ResponseEntity<Object>(categoryService.save(Category), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Actualizar la categoria segun el id: http://localhost:8080/Web/api/v1/categories/{id}
	 * @param id Identificador unico de la categoria
	 * @param product JSON para actualizar la categoria
	 * @return JSON de la categoria actualizada
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable int id,@RequestBody Category category) {
		try {
			category.setId(id);
			return new ResponseEntity<Object>(categoryService.update(category), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(404, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Eliminar una categoria segun el id: http://localhost:8080/Web/api/v1/categories/{id}
	 * @param id Identificador unico de la categoria
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable int id) {
		try {
			Category c = new Category();
			c.setId(id);
			categoryService.delete(c);
			return ResponseEntity.ok().body(null);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(404, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Obtener una categoria segun el id: http://localhost:8080/Web/api/v1/categories/{id}
	 * @param id Identificador unico de la categoria
	 * @return JSON de la categoria selecionada
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable int id) {
		try {
			Category c = categoryService.load(id);
			return new ResponseEntity<Object>(c, HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(404, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
}
