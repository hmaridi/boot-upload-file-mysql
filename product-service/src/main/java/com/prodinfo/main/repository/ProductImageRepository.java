package com.prodinfo.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.prodinfo.main.model.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, String> {

	public ProductImage findByProductId(String productId);
}
