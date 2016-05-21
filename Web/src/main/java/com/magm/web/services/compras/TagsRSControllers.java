package com.magm.web.services.compras;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.magm.compras.business.service.IProductService;
import com.magm.core.bussiness.service.SimpleResponse;
import com.magm.core.bussiness.service.exception.ServiceException;
import com.magm.web.services.Constants;

@RestController
@RequestMapping(value = Constants.URL_TAGS)
public class TagsRSControllers {
	private static Logger LOG = LoggerFactory.getLogger(ProductsRSController.class);
	
	@Autowired
	private IProductService productService;
	
	/**
	 * Obtener una lista de tags:  http://localhost:8080/Web/api/v1/tags
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> tags() {
		try {
			return new ResponseEntity<Object>(productService.listUniqueTags(), HttpStatus.OK);
		} catch (ServiceException e) {
			LOG.error(e.getMessage(), e);
			return new ResponseEntity<Object>(new SimpleResponse(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
