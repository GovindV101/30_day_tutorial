package com.litmus7.retail_product_catalog_manager.exception;

public class ProductInsertException extends Exception {
	
	public ProductInsertException(String message) {
		super(message);
	}
    public ProductInsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
