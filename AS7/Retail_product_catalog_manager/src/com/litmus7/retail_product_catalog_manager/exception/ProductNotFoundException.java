package com.litmus7.retail_product_catalog_manager.exception;

public class ProductNotFoundException extends Exception {
	public ProductNotFoundException(String message) {
		super(message);
	}
	
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}