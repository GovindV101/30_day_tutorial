package com.litmus7.retail_product_catalog_manager.service;

import com.litmus7.retail_product_catalog_manager.dao.*;
import com.litmus7.retail_product_catalog_manager.dto.*;
import com.litmus7.retail_product_catalog_manager.exception.*;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAOImplementation();

    public void addProduct(Product product) throws ProductInsertException {
        productDAO.addProduct(product);
    }

    public Product getProductById(int productId)
            throws ProductNotFoundException, ProductRetrievalException {
        return productDAO.getProductById(productId);
    }

    public List<Product> getAllProducts() throws ProductRetrievalException {
        return productDAO.getAllProducts();
    }

    public void updateProduct(Product product)
            throws ProductUpdateException, ProductNotFoundException {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId)
            throws ProductDeleteException, ProductNotFoundException {
        productDAO.deleteProduct(productId);
    }
    
    public boolean productExists(int productId) throws ProductRetrievalException {
        try {
        	ProductDAOImplementation ProductDAOImplementation = new ProductDAOImplementation();
            Product existing = ProductDAOImplementation.getProductById(productId);
            return existing != null;
        } catch (ProductNotFoundException e) {
            return false;
        }
    }
}
