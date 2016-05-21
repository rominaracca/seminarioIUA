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

import com.magm.compras.business.service.IProductService;
import com.magm.compras.model.Product;
import com.magm.core.bussiness.service.SimpleResponse;
import com.magm.core.bussiness.service.exception.ServiceException;
import com.magm.core.exception.NotFoundException;
import com.magm.web.services.Constants;

//@CrossOrigin(origins={"*"}, methods={"POST"}) Es para todo el controlador
@RestController
@RequestMapping(value = Constants.URL_PRODUCTS)
public class ProductsRSController {
	private static Logger LOG = LoggerFactory.getLogger(ProductsRSController.class);
	
	@Autowired
	private IProductService productService;

	
	/**
	 * Obtener una lista de productos:  http://localhost:8080/Web/api/v1/products
	 * Se puede filtrar por query descrption: http://localhost:8080/Web/api/v1/products?description=Something
	 * @param q filtro
	 * @return JSON de lista de productos
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> list(@RequestParam(value="description", defaultValue="") String q) {
		try {
			return new ResponseEntity<Object>(productService.list(q), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/**
	 * Crear nuevo producto:  http://localhost:8080/Web/api/v1/products
	 * @param product JSON para crear el producto
	 * @return JSON del producto creado
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> save(@RequestBody Product product) {
		try {
			return new ResponseEntity<Object>(productService.save(product), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	/**
	 * Obtener un producto segun el id: http://localhost:8080/Web/api/v1/products/1
	 * @param id Identificador unico del producto
	 * @return JSON del producto selecionado
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> get(@PathVariable int id) {
		try {
			Product p = productService.load(id);
			return new ResponseEntity<Object>(p, HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(404, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	
	/**
	 * Eliminar un producto segun el id: http://localhost:8080/Web/api/v1/products/4
	 * @param id Identificador unico del producto
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable int id) {
		try {
			Product p = new Product();
			p.setId(id);
			productService.delete(p);
			return ResponseEntity.ok().body(null);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(404, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
	 * Actualizar el producto segun el id: http://localhost:8080/Web/api/v1/products/4
	 * @param id Identificador unico del producto
	 * @param product JSON para actualizar el producto
	 * @return JSON del producto actualizado
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Product product) {
		try {
			product.setId(id);
			return new ResponseEntity<Object>(productService.update(product), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Object>(new SimpleResponse(404, e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
}
