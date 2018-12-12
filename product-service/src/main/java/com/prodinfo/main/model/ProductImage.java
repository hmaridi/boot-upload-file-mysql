package com.prodinfo.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "productImage")
public class ProductImage {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String productId;
	private String productName;
	private String productType;
	@Column(columnDefinition = "LONGBLOB") 
	private byte[] productLogo;

	public ProductImage() {
	}

	public ProductImage(String productName, String productType, byte[] productLogo) {
		super();
		this.productName = productName;
		this.productType = productType;
		this.productLogo = productLogo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public byte[] getProductLogo() {
		return productLogo;
	}

	public void setProductLogo(byte[] productLogo) {
		this.productLogo = productLogo;
	}

}
