-- ACCOMODATED THE CHANGES GIVEN, UPDATED THE NAMING CONVENTION BASED ON THE CONNECT, DROPPED SOME INDEXES FOR BETTER PERFOMANCE, ADDED SOME ATTRIBUTES FOR STATUS, ADDED CONSTRAINTS AND DEFAULT VALUES WHERE REQUIRED

use cust_det;
show tables;

DROP TABLE IF EXISTS OrderDetails;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Customers;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE Customers(
	Customer_id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
	Email VARCHAR(100) NOT NULL UNIQUE,
	City VARCHAR(50) NOT NULL,
	Signup_date DATE NOT NULL DEFAULT(CURRENT_DATE),
    Status ENUM('Active','Inactive','Suspended') NOT NULL DEFAULT 'Active',
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT chk_email_format CHECK (Email LIKE '%@%.%')
);

CREATE TABLE Orders(
	Order_id INT AUTO_INCREMENT PRIMARY KEY,
	Customer_id INT NOT NULL,
	Order_date DATE NOT NULL DEFAULT (CURRENT_DATE),
	Total_amount DECIMAL(10,2) NOT NULL,
	Order_status ENUM('Pending', 'Confirmed', 'Shipped', 'Delivered', 'Cancelled') NOT NULL DEFAULT 'Pending',
    Payment_status ENUM('Pending', 'Paid', 'Failed', 'Refunded') NOT NULL DEFAULT 'Pending',
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (Customer_id) REFERENCES Customers(Customer_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT chk_total_amount_positive CHECK (Total_amount > 0)
);

CREATE TABLE Products(
	Product_id INT AUTO_INCREMENT PRIMARY KEY,
	Product_name VARCHAR(100) NOT NULL,
	Category VARCHAR(50) NOT NULL,
	Price DECIMAL(10,2) NOT NULL,
    Status ENUM('Active', 'Discontinued', 'Out_of_Stock') NOT NULL DEFAULT 'Active',
    Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT chk_price_positive CHECK (Price > 0)
);

CREATE TABLE OrderDetails(
	Order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
	Order_id INT NOT NULL,
	Product_id INT NOT NULL,
	Quantity INT NOT NULL,
	Price DECIMAL(10,2) NOT NULL,
	Created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (Order_id) REFERENCES Orders(Order_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Product_id) REFERENCES Products(Product_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT chk_quantity_positive CHECK (Quantity > 0)
);

-- INSERTING SAMPLE DATA TO CUSTOMERS

INSERT INTO Customers (Name, Email, City, Signup_date, Status) VALUES
('Rajesh Kumar', 'rajesh.kumar@email.com', 'Mumbai', '2025-05-15', 'Active'),
('Priya Sharma', 'priya.sharma@email.com', 'Delhi', '2024-08-22', 'Active'),
('Amit Patel', 'amit.patel@email.com', 'Mumbai', '2025-01-10', 'Active'),
('Sneha Gupta', 'sneha.gupta@email.com', 'Bangalore', '2025-02-18', 'Active'),
('Vikram Singh', 'vikram.singh@email.com', 'Chennai', '2024-12-05', 'Active'),
('Anita Reddy', 'anita.reddy@email.com', 'Hyderabad', '2025-03-12', 'Active'),
('Rohit Verma', 'rohit.verma@email.com', 'Mumbai', '2025-04-08', 'Active'),
('Kavya Nair', 'kavya.nair@email.com', 'Kochi', '2024-09-30', 'Inactive'),
('Suresh Agarwal', 'suresh.agarwal@email.com', 'Pune', '2025-01-25', 'Active'),
('Meera Joshi', 'meera.joshi@email.com', 'Delhi', '2024-11-14', 'Active');

-- INSERTING DATA INTO PRODUCTS TABLE

INSERT INTO Products (Product_name, Category, Price, Status) VALUES
('Samsung Galaxy S24', 'Electronics', 75000.00, 'Active'),
('Nike Air Max', 'Footwear', 8500.00, 'Active'),
('Levi\'s Jeans', 'Clothing', 3500.00,  'Active'),
('MacBook Pro', 'Electronics', 150000.00,  'Active'),
('Adidas Running Shoes', 'Footwear', 6500.00,  'Active'),
('Cotton T-Shirt', 'Clothing', 1200.00,  'Active'),
('iPhone 15', 'Electronics', 85000.00,  'Active'),
('Formal Shirt', 'Clothing', 2500.00,  'Active'),
('Wireless Headphones', 'Electronics', 12000.00,  'Active'),
('Casual Sneakers', 'Footwear', 4500.00,  'Active'),
('Gaming Laptop', 'Electronics', 95000.00,  'Active'),
('Designer Dress', 'Clothing', 4800.00, 'Out_of_Stock');

-- INSERTING DATA INTO ORDERS TABLE

INSERT INTO Orders (Customer_id, Order_date, Total_amount, Order_status, Payment_status) VALUES
(1, '2025-05-15', 78600.00, 'Delivered', 'Paid'),
(2, '2025-05-18', 15700.00, 'Delivered', 'Paid'),
(3, '2025-05-20', 7000.00, 'Delivered', 'Paid'),
(4, '2025-05-22', 152500.00, 'Shipped', 'Paid'),
(5, '2025-05-25', 4500.00, 'Delivered', 'Paid'),
(1, '2025-05-28', 12000.00, 'Confirmed', 'Paid'),
(6, '2025-05-30', 97500.00, 'Shipped', 'Paid'),
(7, '2025-06-01', 6500.00, 'Delivered', 'Paid'),
(8, '2025-06-03', 1200.00, 'Cancelled', 'Refunded'),
(2, '2025-06-05', 85000.00, 'Pending', 'Pending'),
(3, '2025-04-10', 3500.00, 'Delivered', 'Paid'),
(4, '2025-04-15', 8500.00, 'Delivered', 'Paid');

-- INSERTING DATA INTO ORDERDETAILS TABLE

INSERT INTO OrderDetails (Order_id, Product_id, Quantity, Price) VALUES
(1, 1, 1, 75000.00),
(1, 6, 3,  3600.00),
(2, 9, 1,  12000.00),
(2, 6, 2,  2400.00),
(2, 8, 1,  2500.00),
(3, 3, 2,  7000.00),
(4, 4, 1,  150000.00),
(4, 8, 1,  2500.00),
(5, 10, 1,  4500.00),
(6, 9, 1,  12000.00),
(7, 11, 1,  95000.00),
(7, 8, 1,  2500.00),
(8, 5, 1,  6500.00),
(9, 6, 1,  1200.00),
(10, 7, 1,  85000.00),
(11, 3, 1,  3500.00),
(12, 2, 1,  8500.00);

-- (USED CHATGPT TO FIND WHICH ALL FIELDS NEEDED INDEXES, UNDERSTOOD ITS EXPLANATION)
-- UPDATE(REMOVED SOME INDEXES)
CREATE INDEX idx_orders_customerid ON Orders(Customer_id);
CREATE INDEX idx_orderdetails_orderid ON OrderDetails(Order_id);
CREATE INDEX idx_orderdetails_productid ON OrderDetails(Product_id);
CREATE INDEX idx_customers_city ON Customers(City);
CREATE INDEX idx_orders_orderdate ON Orders(Order_date);
CREATE INDEX idx_orders_customer_date ON Orders(Customer_id, Order_date);


-- BASIC QUERIES

-- 1)  Get the list of all customers.

SELECT Customer_id, Name, Email, City, Signup_date, Status, Created_at
FROM Customers;

-- 2)  Find all orders placed in the last 30 days.

SELECT Order_id, Customer_id, Order_date, Total_amount, Order_status, Payment_status FROM Orders 
WHERE Order_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY);

-- 3) Show product names and their prices.

SELECT Product_name, Price FROM Products;

-- 4) Find the total number of products in each category.

SELECT Category,COUNT(Product_id) AS No_Of_Products FROM Products GROUP BY Category;

-- FILTERING AND CONDITIONS

-- 5) Get all customers from the city 'Mumbai'.

SELECT Customer_id, Name, Email, City, Signup_date, Status FROM Customers 
WHERE City = 'Mumbai' AND Status = 'Active';

-- 6) Find orders with a total amount greater than ₹5000

SELECT Order_id, Customer_id, Order_date, Total_amount, Order_status, Payment_status FROM Orders 
WHERE Total_amount > 5000 AND Order_status != 'Cancelled';

-- 7) List customers who signed up after '2024-01-01'

SELECT Customer_id, Name, Email, City, Signup_date, Status FROM Customers WHERE Signup_date>'2024-01-01';

-- JOINS

-- 8) Show all orders along with the customer's name.

SELECT Orders.Order_id,Customers.Name FROM Orders JOIN Customers ON Orders.Customer_id = Customers.Customer_id;

-- 9) Order details with product information
SELECT o.Order_id, p.Product_name, od.Quantity, od.Price
FROM Orders o
JOIN OrderDetails od ON o.Order_id = od.Order_id
JOIN Products p ON od.Product_id = p.Product_id
ORDER BY o.Order_id,p.Product_name;

-- 10) Find customers who have never placed an order
SELECT Customer_id, Name , c.Status
FROM Customers c 
WHERE Customer_id NOT IN (SELECT Customer_id FROM Orders);

-- AGGREGATION AND GROUPING
-- 11) Find the total amount spent by each customer
SELECT c.Name, SUM(o.Total_amount) as TotalSpent
FROM Customers c
JOIN Orders o ON c.Customer_id = o.Customer_id
GROUP BY c.Customer_id, c.Name
ORDER BY TotalSpent DESC;

-- 12) Which product has been sold the most (by quantity)?
SELECT p.Product_name, SUM(od.Quantity) as TotalQuantity
FROM Products p
JOIN OrderDetails od ON p.Product_id = od.Product_id
JOIN Orders o ON od.Order_id=o.Order_id
WHERE o.Order_status != 'Cancelled'
GROUP BY p.Product_id, p.Product_name
ORDER BY TotalQuantity DESC
LIMIT 1;

-- 13) Find the average order value for each customer
SELECT c.Name, AVG(o.Total_amount) as AverageOrderValue
FROM Customers c
JOIN Orders o ON c.Customer_id = o.Customer_id
WHERE o.Order_status != 'Cancelled'
GROUP BY c.Customer_id, c.Name
ORDER BY AverageOrderValue DESC;

-- 14) Total sales amount per product category
SELECT p.Category, SUM(od.Quantity * od.Price) as TotalSales
FROM Products p
JOIN OrderDetails od ON p.Product_id = od.Product_id
JOIN Orders o ON od.Order_id = o.Order_id
WHERE o.Order_status != 'Cancelled'
GROUP BY p.Category
ORDER BY TotalSales DESC;

-- SUBQUERIES
-- 15) Find customers who spent more than the average spending
SELECT c.Name, SUM(o.Total_amount) as TotalSpent
FROM Customers c
JOIN Orders o ON c.Customer_id = o.Customer_id
WHERE o.Order_status != 'Cancelled'
GROUP BY c.Customer_id, c.Name
HAVING SUM(o.Total_amount) > (
    SELECT AVG(CustomerTotal) 
    FROM (
        SELECT SUM(Total_amount) as CustomerTotal 
        FROM Orders 
        GROUP BY Customer_id
    ) as CustomerTotals
);

-- 16) List products that have never been ordered
SELECT p.Product_id, p.Product_name, p.Category, p.Price
FROM Products p
LEFT JOIN OrderDetails od ON p.Product_id = od.Product_id
WHERE od.Product_id IS NULL;

-- 17) Find the most recent order for each customer
SELECT c.Name, o.Order_id, o.Order_date, o.Total_amount
FROM Customers c
JOIN Orders o ON c.Customer_id = o.Customer_id
WHERE o.Order_date = (
    SELECT MAX(Order_date) 
    FROM Orders o2 
    WHERE o2.Customer_id = c.Customer_id
)
ORDER BY c.Name;

-- ADVANCED QUERIES
-- 18) Rank customers by total spending (highest first)
SELECT c.Name, SUM(o.Total_amount) as TotalSpending, RANK() OVER (ORDER BY SUM(o.Total_amount) DESC) as SpendingRank
FROM Customers c
JOIN Orders o ON c.Customer_id = o.Customer_id
GROUP BY c.Customer_id, c.Name
ORDER BY TotalSpending DESC;

-- 19) Get the top 3 customers based on the number of orders placed
SELECT c.Name, COUNT(o.Order_id) as OrderCount
FROM Customers c
JOIN Orders o ON c.Customer_id = o.Customer_id
GROUP BY c.Customer_id, c.Name
ORDER BY OrderCount DESC
LIMIT 3;

-- 20) For each product, find how many unique customers have purchased
SELECT p.Product_name, COUNT(DISTINCT o.Customer_id) as UniqueCustomers
FROM Products p
JOIN OrderDetails od ON p.Product_id = od.Product_id
JOIN Orders o ON od.Order_id = o.Order_id
WHERE o.Order_status != 'Cancelled'
GROUP BY p.Product_id, p.Product_name
ORDER BY UniqueCustomers DESC;