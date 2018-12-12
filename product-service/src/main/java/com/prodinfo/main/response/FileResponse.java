package com.prodinfo.main.response;


public class FileResponse {
	private String productName;
	private String downloadUri;
	private String productType;
	private long imageSize;

	public FileResponse(String productName, String downloadUri, String productType, long imageSize) {
		super();
		this.productName = productName;
		this.downloadUri = downloadUri;
		this.productType = productType;
		this.imageSize = imageSize;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDownloadUri() {
		return downloadUri;
	}

	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public long getImageSize() {
		return imageSize;
	}

	public void setImageSize(long imageSize) {
		this.imageSize = imageSize;
	}

}
