package com.litmus7.retail_product_catalog_manager.exception;

public class ProductDeleteException extends Exception{
	
	public ProductDeleteException(String message) {
		super(message);
	}
	public ProductDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}