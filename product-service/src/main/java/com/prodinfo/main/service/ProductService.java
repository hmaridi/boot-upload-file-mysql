package com.prodinfo.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prodinfo.main.model.Product;
import com.prodinfo.main.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public Product fetchById(Integer id) {
		return productRepository.getById(id);
	}

	public List<Product> fetchAll() {
		return productRepository.findAll();
	}

	public void deleteById(Integer id) {
		productRepository.delete(id);
	}
}
