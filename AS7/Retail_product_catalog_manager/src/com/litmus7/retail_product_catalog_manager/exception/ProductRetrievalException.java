package com.litmus7.retail_product_catalog_manager.exception;

public class ProductRetrievalException extends Exception{
	
	public ProductRetrievalException(String message) {
		super(message);
	}
	public ProductRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}