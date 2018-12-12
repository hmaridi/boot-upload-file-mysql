package com.prodinfo.main.exception;

public class ImageStorageException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ImageStorageException(String message) {
		super(message);
	}

	public ImageStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
