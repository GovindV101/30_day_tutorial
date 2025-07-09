package com.litmus7.retail_product_catalog_manager.ui;

import com.litmus7.retail_product_catalog_manager.dto.*;
import com.litmus7.retail_product_catalog_manager.model.*;
import com.litmus7.retail_product_catalog_manager.contoller.*;
import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;

public class ProductApp {
	public static void main(String[] args) {
		ProductController controller = new ProductController();
		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n===== Product Management Menu =====");
			System.out.println("1. Add Product");
			System.out.println("2. View Product by ID");
			System.out.println("3. View All Products");
			System.out.println("4. Update Product");
			System.out.println("5. Delete Product");
			System.out.println("6. Exit");
			System.out.print("Enter choice: ");

			try {
				choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1 -> {
					Product product = readProductInput(scanner, false);
					printResponse(controller.addProduct(product));
				}
				case 2 -> {
					int id = readProductId(scanner);
					printResponse(controller.viewProductById(id));
				}
				case 3 -> printResponse(controller.viewAllProducts());
				case 4 -> {
					int id = readProductId(scanner);
					Product updatedProduct = readProductInputForUpdate(id, scanner, true);
					updatedProduct.setProductId(id);
					printResponse(controller.updateProduct(updatedProduct));
				}
				case 5 -> {
					int id = readProductId(scanner);
					printResponse(controller.deleteProduct(id));
				}
				case 6 -> System.out.println("Exiting... Goodbye!");
				default -> System.out.println("Invalid choice. Please try again.");
				}

			} catch (InputMismatchException e) {
				System.err.println("Invalid input. Please enter a number.");
				scanner.nextLine();
				choice = 0;
			} catch (Exception e) {
				System.err.println("Unexpected error: " + e.getMessage());
				scanner.nextLine();
				choice = 0;
			}

		} while (choice != 6);

		scanner.close();
	}

	private static Product readProductInput(Scanner scanner, boolean isUpdate) {
		System.out.print("ProductID: ");
		int productID = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Name: ");
		String name = scanner.nextLine();

		System.out.print("Category: ");
		String category = scanner.nextLine();

		System.out.print("Price: ");
		double price = scanner.nextDouble();
		scanner.nextLine();

		System.out.print("Stock Quantity: ");
		int stock = scanner.nextInt();
		scanner.nextLine();

		return new Product(productID, name, category, price, stock);
	}

	private static Product readProductInputForUpdate(int id, Scanner scanner, boolean isUpdate) {

		int productID = id;
		System.out.print("Name: ");
		String name = scanner.nextLine();

		System.out.print("Category: ");
		String category = scanner.nextLine();

		System.out.print("Price: ");
		double price = scanner.nextDouble();
		scanner.nextLine();

		System.out.print("Stock Quantity: ");
		int stock = scanner.nextInt();
		scanner.nextLine();

		return new Product(productID, name, category, price, stock);
	}

	private static int readProductId(Scanner scanner) {
		System.out.print("Enter Product ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		return id;
	}

	private static <T> void printResponse(Response<T> response) {
		if (response.isSuccess()) {
			System.out.println(response.getMessage());
			if (response.getData() instanceof List<?> list) {
				list.forEach(System.out::println);
			} else if (response.getData() != null) {
				System.out.println(response.getData());
			}
		} else {
			System.err.println(response.getMessage());
			if (response.getErrorCode() != null) {
				System.err.println("Error Code: " + response.getErrorCode());
			}
		}
	}
}
