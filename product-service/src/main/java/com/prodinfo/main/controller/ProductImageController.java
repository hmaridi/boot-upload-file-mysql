package com.prodinfo.main.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;

import com.prodinfo.main.model.ProductImage;
import com.prodinfo.main.response.FileResponse;
import com.prodinfo.main.service.ProductImageService;
import com.prodinfo.main.utils.UrlConstants;

@RestController
@RequestMapping(value=UrlConstants.API_BASE)
public class ProductImageController {
	private static final Logger logger = LoggerFactory.getLogger(ProductImageController.class);

	@Autowired
	private ProductImageService productImageService;

	@RequestMapping(value = UrlConstants.Product.UPLOAD_IMAGE, method = RequestMethod.POST)
	public FileResponse uploadImage(@RequestParam("image") MultipartFile image) {
		ProductImage productImage = productImageService.saveProductImage(image);
		String imageURI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadimage/")
				.path(productImage.getProductId()).toUriString();
		FileResponse fileResponse = new FileResponse(productImage.getProductName(), imageURI, image.getContentType(),
				image.getSize());
		return fileResponse;
	}

	@RequestMapping(value = UrlConstants.Product.UPLOAD_IMAGES, method = RequestMethod.POST,headers=("content-type=multipart/form-data"))
	 public List<FileResponse> uploadImages(@RequestParam("images") MultipartFile[] images) {
        return Arrays.asList(images)
                .stream()
                .map(image -> uploadImage(image))
                .collect(Collectors.toList());
    }

	@RequestMapping(value = UrlConstants.Product.DOWNLOAD_IMAGE, method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadImage(@RequestParam String productId) {
		ProductImage productImage = productImageService.productImageId(productId);
		logger.info("Download image details " + productId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(productImage.getProductType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + productImage.getProductName() + "\"")
				.body(new ByteArrayResource(productImage.getProductLogo()));

	}

}
