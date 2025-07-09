package com.litmus7.retail_product_catalog_manager.dao;

import com.litmus7.retail_product_catalog_manager.dto.*;
import com.litmus7.retail_product_catalog_manager.exception.*;
import com.litmus7.retail_product_catalog_manager.util.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImplementation implements ProductDAO {
	@Override
	public void addProduct(Product product) throws ProductInsertException {
		String sql = "INSERT INTO products (productId, name, category, price, stockQuantity) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, product.getProductId());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getCategory());
			stmt.setDouble(4, product.getPrice());
			stmt.setInt(5, product.getStockQuantity());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new ProductInsertException("Failed to add product to the database", e);
		}
	}

	@Override
	public Product getProductById(int productId) throws ProductNotFoundException, ProductRetrievalException {

		String sql = "SELECT * FROM products WHERE productId = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Product(rs.getInt("productId"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getInt("stockQuantity"));
			} else {
				throw new ProductNotFoundException("Product with ID " + productId + " not found.");
			}

		} catch (SQLException e) {
			throw new ProductRetrievalException("Error retrieving product by ID", e);
		}
	}

	@Override
	public List<Product> getAllProducts() throws ProductRetrievalException {
		String sql = "SELECT * FROM products";
		List<Product> products = new ArrayList<>();

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				products.add(new Product(rs.getInt("productId"), rs.getString("name"), rs.getString("category"),
						rs.getDouble("price"), rs.getInt("stockQuantity")));
			}

		} catch (SQLException e) {
			throw new ProductRetrievalException("Failed to retrieve products", e);
		}

		return products;
	}

	@Override
	public void updateProduct(Product product) throws ProductUpdateException, ProductNotFoundException {

		String sql = "UPDATE products SET name = ?, category = ?, price = ?, stockQuantity = ? WHERE productId = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, product.getName());
			stmt.setString(2, product.getCategory());
			stmt.setDouble(3, product.getPrice());
			stmt.setInt(4, product.getStockQuantity());
			stmt.setInt(5, product.getProductId());

			int affectedRows = stmt.executeUpdate();
			if (affectedRows == 0) {
				throw new ProductNotFoundException(
						"Product with ID " + product.getProductId() + " not found for update.");
			}

		} catch (SQLException e) {
			throw new ProductUpdateException("Failed to update product", e);
		}
	}

	@Override
	public void deleteProduct(int productId) throws ProductDeleteException, ProductNotFoundException {

		String sql = "DELETE FROM products WHERE productId = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, productId);
			int affectedRows = stmt.executeUpdate();

			if (affectedRows == 0) {
				throw new ProductNotFoundException("Product with ID " + productId + " not found for deletion.");
			}

		} catch (SQLException e) {
			throw new ProductDeleteException("Failed to delete product", e);
		}
	}
}
