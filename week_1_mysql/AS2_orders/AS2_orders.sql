use cust_det;
show tables;
CREATE TABLE Customers(
	CustomerID INT PRIMARY KEY,
    Name VARCHAR(100),
	Email VARCHAR(100),
	City VARCHAR(50),
	SignupDate DATE
);

CREATE TABLE Orders(
	OrderID INT PRIMARY KEY,
	CustomerID INT,
	OrderDate DATE,
	TotalAmount DECIMAL(10,2),
	FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Products(
	ProductID INT PRIMARY KEY,
	ProductName VARCHAR(100),
	Category VARCHAR(50),
	Price DECIMAL(10,2)
);

CREATE TABLE OrderDetails(
	OrderDetailID INT PRIMARY KEY,
	OrderID INT,
	ProductID INT,
	Quantity INT,
	Price DECIMAL(10,2),
	FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
	FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- INSERTING SAMPLE DATA TO CUSTOMERS

INSERT INTO Customers (CustomerID, Name, Email, City, SignupDate) VALUES
(1, 'Rajesh Kumar', 'rajesh.kumar@email.com', 'Mumbai', '2023-05-15'),
(2, 'Priya Sharma', 'priya.sharma@email.com', 'Delhi', '2023-08-22'),
(3, 'Amit Patel', 'amit.patel@email.com', 'Mumbai', '2024-01-10'),
(4, 'Sneha Gupta', 'sneha.gupta@email.com', 'Bangalore', '2024-02-18'),
(5, 'Vikram Singh', 'vikram.singh@email.com', 'Chennai', '2023-12-05'),
(6, 'Anita Reddy', 'anita.reddy@email.com', 'Hyderabad', '2024-03-12'),
(7, 'Rohit Verma', 'rohit.verma@email.com', 'Mumbai', '2024-04-08'),
(8, 'Kavya Nair', 'kavya.nair@email.com', 'Kochi', '2023-09-30'),
(9, 'Suresh Agarwal', 'suresh.agarwal@email.com', 'Pune', '2024-01-25'),
(10, 'Meera Joshi', 'meera.joshi@email.com', 'Delhi', '2023-11-14');

-- INSERTING DATA INTO PRODUCTS TABLE

INSERT INTO Products (ProductID, ProductName, Category, Price) VALUES
(1, 'Samsung Galaxy S24', 'Electronics', 75000.00),
(2, 'Nike Air Max', 'Footwear', 8500.00),
(3, 'Levi\'s Jeans', 'Clothing', 3500.00),
(4, 'MacBook Pro', 'Electronics', 150000.00),
(5, 'Adidas Running Shoes', 'Footwear', 6500.00),
(6, 'Cotton T-Shirt', 'Clothing', 1200.00),
(7, 'iPhone 15', 'Electronics', 85000.00),
(8, 'Formal Shirt', 'Clothing', 2500.00),
(9, 'Wireless Headphones', 'Electronics', 12000.00),
(10, 'Casual Sneakers', 'Footwear', 4500.00),
(11, 'Gaming Laptop', 'Electronics', 95000.00),
(12, 'Designer Dress', 'Clothing', 4800.00);

-- INSERTING DATA INTO ORDERS TABLE

INSERT INTO Orders (OrderID, CustomerID, OrderDate, TotalAmount) VALUES
(1, 1, '2025-05-15', 78500.00),
(2, 2, '2025-05-18', 15000.00),
(3, 3, '2025-05-20', 7000.00),
(4, 4, '2025-05-22', 152500.00),
(5, 5, '2025-05-25', 4500.00),
(6, 1, '2025-05-28', 12000.00),
(7, 6, '2025-05-30', 97500.00),
(8, 7, '2025-06-01', 6000.00),
(9, 8, '2025-06-03', 1200.00),
(10, 2, '2025-06-05', 13000.00),
(11, 3, '2025-04-10', 3500.00),
(12, 4, '2025-04-15', 8500.00);

-- INSERTING DATA INTO ORDERDETAILS TABLE

INSERT INTO OrderDetails (OrderDetailID, OrderID, ProductID, Quantity, Price) VALUES
(1, 1, 1, 1, 75000.00),
(2, 1, 6, 3, 1200.00),
(3, 2, 9, 1, 12000.00),
(4, 2, 6, 2, 1200.00),
(5, 2, 8, 1, 2500.00),
(6, 3, 3, 2, 3500.00),
(7, 4, 4, 1, 150000.00),
(8, 4, 8, 1, 2500.00),
(9, 5, 10, 1, 4500.00),
(10, 6, 9, 1, 12000.00),
(11, 7, 11, 1, 95000.00),
(12, 7, 8, 1, 2500.00),
(13, 8, 5, 1, 6500.00),
(14, 9, 6, 1, 1200.00),
(15, 10, 7, 1, 85000.00),
(16, 11, 3, 1, 3500.00),
(17, 12, 2, 1, 8500.00);

-- (USED CHATGPT TO FIND WHICH ALL FIELDS NEEDED INDEXES, UNDERSTOOD ITS EXPLANATION)

CREATE INDEX idx_orders_customerid ON Orders(CustomerID);
CREATE INDEX idx_orderdetails_orderid ON OrderDetails(OrderID);
CREATE INDEX idx_orderdetails_productid ON OrderDetails(ProductID);
CREATE INDEX idx_customers_city ON Customers(City);
CREATE INDEX idx_orders_orderdate ON Orders(OrderDate);
CREATE INDEX idx_orders_totalamount ON Orders(TotalAmount);
CREATE INDEX idx_customers_signupdate ON Customers(SignupDate);
CREATE INDEX idx_products_category_price ON Products(Category, Price);
CREATE INDEX idx_orders_customer_date ON Orders(CustomerID, OrderDate);


-- BASIC QUERIES

-- 1)  Get the list of all customers.

SELECT * FROM Customers;

-- 2)  Find all orders placed in the last 30 days.

SELECT * FROM Orders 
WHERE OrderDate >= DATE_SUB(CURDATE(), INTERVAL 30 DAY);

-- 3) Show product names and their prices.

SELECT ProductName, Price FROM Products;

-- 4) Find the total number of products in each category.

SELECT Category,COUNT(ProductID) AS No_Of_Products FROM Products GROUP BY Category;

-- FILTERING AND CONDITIONS

-- 5) Get all customers from the city 'Mumbai'.

SELECT * FROM Customers WHERE City = 'Mumbai';

-- 6) Find orders with a total amount greater than ₹5000

SELECT * FROM Orders WHERE TotalAmount>5000;

-- 7) List customers who signed up after '2024-01-01'

SELECT * FROM Customers WHERE SignupDate>'2024-01-01';

-- JOINS

-- 8) Show all orders along with the customer's name.

SELECT Orders.OrderID,Customers.Name FROM Orders JOIN Customers ON Orders.CustomerID = Customers.CustomerID;

-- 9) List products purchased in each order.

SELECT o.OrderID, p.ProductName, od.Quantity, od.Price
FROM Orders o
JOIN OrderDetails od ON o.OrderID = od.OrderID
JOIN Products p ON od.ProductID = p.ProductID
ORDER BY o.OrderID;

-- 10) Find customers who have never placed an order

SELECT CustomerID,Customers.Name FROM Customers WHERE CustomerID NOT IN (SELECT CustomerID FROM Orders);

-- AGGREGATION AND GROUPING

-- 11) Find the total amount spent by each customer.

SELECT c.Name, SUM(o.TotalAmount) as TotalSpent
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.Name
ORDER BY TotalSpent;

-- 12) Which product has been sold the most (by quantity)?

SELECT p.ProductName, SUM(od.Quantity)
FROM Products p
JOIN OrderDetails od ON p.ProductID = od.ProductID
GROUP BY p.ProductID, p.ProductName
ORDER BY SUM(od.Quantity) DESC
LIMIT 1;

-- 13) Find the average order value for each customer

SELECT c.Name, AVG(o.TotalAmount) as AverageOrderValue
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.Name
ORDER BY AverageOrderValue DESC;

-- 14) Total sales amount per product category

SELECT p.Category, SUM(od.Quantity * od.Price) as TotalSales
FROM Products p
JOIN OrderDetails od ON p.ProductID = od.ProductID
GROUP BY p.Category
ORDER BY TotalSales DESC;

-- SUBQUERIES

-- 15) Find customers who spent more than the average spending.

SELECT c.Name, SUM(o.TotalAmount) as TotalSpent
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.Name
HAVING SUM(o.TotalAmount) > (
    SELECT AVG(CustomerTotal) 
    FROM (
        SELECT SUM(TotalAmount) as CustomerTotal 
        FROM Orders 
        GROUP BY CustomerID
    ) as CustomerTotals
);

-- 16) List products that have never been ordered

SELECT p.ProductID, p.ProductName, p.Category, p.Price
FROM Products p
LEFT JOIN OrderDetails od ON p.ProductID = od.ProductID
WHERE od.ProductID IS NULL;

-- 17) Find the most recent order for each customer

SELECT c.Name, o.OrderID, o.OrderDate, o.TotalAmount
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
WHERE o.OrderDate = (
    SELECT MAX(OrderDate) 
    FROM Orders o2 
    WHERE o2.CustomerID = c.CustomerID
)
ORDER BY c.Name;

-- ADVANCED QUERIES

-- 18) Rank customers by total spending (highest first)

SELECT c.Name,SUM(o.TotalAmount) as TotalSpending,RANK() OVER (ORDER BY SUM(o.TotalAmount) DESC) as SpendingRank
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.Name
ORDER BY TotalSpending DESC;

-- 19) Get the top 3 customers based on the number of orders placed

SELECT c.Name,COUNT(o.OrderID) as OrderCount
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.Name
ORDER BY OrderCount DESC
LIMIT 3;

-- 20) For each product, find how many unique customers have purchased

SELECT p.ProductName,COUNT(DISTINCT o.CustomerID) as UniqueCustomers
FROM Products p
JOIN OrderDetails od ON p.ProductID = od.ProductID
JOIN Orders o ON od.OrderID = o.OrderID
GROUP BY p.ProductID, p.ProductName
ORDER BY UniqueCustomers DESC;