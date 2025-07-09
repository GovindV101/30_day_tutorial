package com.litmus7.retail_product_catalog_manager.exception;

public class DuplicateProductException extends Exception {
	
	public DuplicateProductException(String message,Throwable cause) {
			super(message,cause);
	}
	public DuplicateProductException(String message) {
		super(message);
	}
}
