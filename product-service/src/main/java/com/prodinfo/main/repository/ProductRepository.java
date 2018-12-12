package com.prodinfo.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodinfo.main.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	public Product getById(Integer id);
}
