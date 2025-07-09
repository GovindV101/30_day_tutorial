package com.litmus7.retail_product_catalog_manager.exception;

public class ProductUpdateException extends Exception{
	
	public ProductUpdateException(String message) {
		super(message);
	}
	public ProductUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
