package com.litmus7.retail_product_catalog_manager.dao;

import com.litmus7.retail_product_catalog_manager.dto.*;
import com.litmus7.retail_product_catalog_manager.exception.*;
import java.util.List;

public interface ProductDAO {
	void addProduct(Product product) throws ProductInsertException;

	Product getProductById(int productId) throws ProductNotFoundException, ProductRetrievalException;

	List<Product> getAllProducts() throws ProductRetrievalException;

	void updateProduct(Product product) throws ProductUpdateException, ProductNotFoundException;

	void deleteProduct(int productId) throws ProductDeleteException, ProductNotFoundException;
}
