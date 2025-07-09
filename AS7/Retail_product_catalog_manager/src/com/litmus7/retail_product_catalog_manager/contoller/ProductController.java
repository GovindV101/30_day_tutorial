package com.litmus7.retail_product_catalog_manager.contoller;

import com.litmus7.retail_product_catalog_manager.service.*;
import com.litmus7.retail_product_catalog_manager.exception.*;
import com.litmus7.retail_product_catalog_manager.model.*;
import com.litmus7.retail_product_catalog_manager.dto.*;
import java.util.List;

public class ProductController {
	private final ProductService service = new ProductService();

	public Response<Void> addProduct(Product product) {
		try {
			String validationError = validateProductInput(product, false);
			if (validationError != null) {
				return Response.error(validationError, "VALIDATION_ERROR");
			}
			if (service.productExists(product.getProductId())) {
				return Response.error("Product ID already exists. Please use a different ID.", "DUPLICATE_PRODUCT_ID");
			}
			service.addProduct(product);
			return Response.success("Product added successfully.");
		} catch (ProductInsertException e) {
			return Response.error("Error adding product: " + e.getMessage(), "PRODUCT_INSERT_ERROR");
		} catch (Exception e) {
			return Response.error("An unexpected error occurred. Please contact support.", "INTERNAL_ERROR");
		}
	}

	public Response<Product> viewProductById(int id) {
		try {
			if (id <= 0) {
				return Response.error("Product ID must be a positive integer.", "VALIDATION_ERROR");
			}

			Product product = service.getProductById(id);
			return Response.success("Product found.", product);
		} catch (ProductNotFoundException e) {
			return Response.error(e.getMessage(), "PRODUCT_NOT_FOUND");
		} catch (ProductRetrievalException e) {
			return Response.error("Error retrieving product: " + e.getMessage(), "PRODUCT_RETRIEVAL_ERROR");
		} catch (Exception e) {
			return Response.error("An unexpected error occurred. Please contact support.", "INTERNAL_ERROR");
		}
	}

	public Response<List<Product>> viewAllProducts() {
		try {
			List<Product> products = service.getAllProducts();
			if (products.isEmpty()) {
				return Response.success("No products found.", products);
			} else {
				return Response.success("Products retrieved successfully.", products);
			}
		} catch (ProductRetrievalException e) {
			return Response.error("Error retrieving products: " + e.getMessage(), "PRODUCT_RETRIEVAL_ERROR");
		} catch (Exception e) {
			return Response.error("An unexpected error occurred. Please contact support.", "INTERNAL_ERROR");
		}
	}

	public Response<Void> updateProduct(Product product) {
		try {
			String validationError = validateProductInput(product, true);
			if (validationError != null) {
				return Response.error(validationError, "VALIDATION_ERROR");
			}

			service.updateProduct(product);
			return Response.success("Product updated successfully.");
		} catch (ProductNotFoundException e) {
			return Response.error(e.getMessage(), "PRODUCT_NOT_FOUND");
		} catch (ProductUpdateException e) {
			return Response.error("Failed to update: " + e.getMessage(), "PRODUCT_UPDATE_ERROR");
		} catch (Exception e) {
			return Response.error("An unexpected error occurred. Please contact support.", "INTERNAL_ERROR");
		}
	}

	public Response<Void> deleteProduct(int id) {
		try {
			if (id <= 0) {
				return Response.error("Product ID must be a positive integer.", "VALIDATION_ERROR");
			}

			service.deleteProduct(id);
			return Response.success("Product deleted successfully.");
		} catch (ProductNotFoundException e) {
			return Response.error(e.getMessage(), "PRODUCT_NOT_FOUND");
		} catch (ProductDeleteException e) {
			return Response.error("Error deleting product: " + e.getMessage(), "PRODUCT_DELETE_ERROR");
		} catch (Exception e) {
			return Response.error("An unexpected error occurred. Please contact support.", "INTERNAL_ERROR");
		}
	}

	private String validateProductInput(Product product, boolean isUpdate) {
		if (product == null)
			return "Product cannot be null.";
		if (isUpdate && product.getProductId() <= 0)
			return "Product ID must be a positive integer.";
		if (product.getName() == null || product.getName().trim().isEmpty())
			return "Product name cannot be empty.";
		if (product.getCategory() == null || product.getCategory().trim().isEmpty())
			return "Product category cannot be empty.";
		if (product.getPrice() < 0)
			return "Price cannot be negative.";
		if (product.getStockQuantity() < 0)
			return "Stock quantity cannot be negative.";
		return null;
	}

}