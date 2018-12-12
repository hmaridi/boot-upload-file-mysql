package com.prodinfo.main.controller;

import java.util.List;
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

import com.prodinfo.main.model.Product;
import com.prodinfo.main.service.ProductService;
import com.prodinfo.main.utils.UrlConstants;

@RestController
@RequestMapping(value=UrlConstants.API_BASE)
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@RequestMapping(value = UrlConstants.Product.PRODUCTS_URI, method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		Product products = productService.createProduct(product);
		logger.info("Adding Product Details " + products);
		return new ResponseEntity<Object>("Product has been created successfully", HttpStatus.CREATED);
	}

	@RequestMapping(value = UrlConstants.Product.PRODUCTS_BY_ID_URI, method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@RequestParam("id") Integer id) {
		Product product = productService.fetchById(id);
		if (product == null) {
			logger.info("Product with id " + id + " does not exists");
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		logger.info("Getting product details for ID " + id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = UrlConstants.Product.GET_ALL_PRODUCTS, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listOfProducts() {
		List<Product> products = productService.fetchAll();
		if (products.isEmpty()) {
			logger.info("Products does not exists");
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
		logger.info("Found " + products.size() + " Products");
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = UrlConstants.Product.DELETE_BY_PRODUCT, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(@RequestParam("id") Integer id) {
		Product product = productService.fetchById(id);
		if (product == null) {
			logger.info("Product with id " + id + " does not exists");
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		} else {
			productService.deleteById(id);
			logger.info("Product with id " + id + " deleted");
			return new ResponseEntity<String>("Product has been deleted successfully",HttpStatus.GONE);
		}
	}

}
