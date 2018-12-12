package com.prodinfo.main.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.prodinfo.main.exception.ImageNotFoundException;
import com.prodinfo.main.exception.ImageStorageException;
import com.prodinfo.main.model.ProductImage;
import com.prodinfo.main.repository.ProductImageRepository;

@Service
public class ProductImageService {

	@Autowired
	private ProductImageRepository productImageRepository;

	public ProductImage saveProductImage(MultipartFile file) {
		String productName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (productName.contains("..")) {
				throw new ImageStorageException("Sorry! Filename contains invalid path sequence " + productName);
			}
			ProductImage productImage = new ProductImage(productName, file.getContentType(), file.getBytes());
			return productImageRepository.save(productImage);
		} catch (IOException ex) {
			throw new ImageStorageException("Could not store productImage " + productName + ". Please try again!", ex);
		}
	}

	public ProductImage productImageId(String productId) {
		try {
			return productImageRepository.findByProductId(productId);
		} catch (Exception ex) {
			throw new ImageNotFoundException("File not found with id " + productId + ".Please try again!", ex);
		}

	}
}
