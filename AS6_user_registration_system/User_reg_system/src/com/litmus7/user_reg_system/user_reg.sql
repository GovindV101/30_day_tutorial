
CREATE DATABASE user_registration;
USE user_registration;


CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT chk_age CHECK (age >= 18 AND age <= 60),
    CONSTRAINT chk_email CHECK (email LIKE '%@%.%')
);


CREATE VIEW user_profile AS
SELECT 
    id,
    username,
    age,
    email,
    created_at,
    updated_at
FROM users;

DELIMITER //
CREATE PROCEDURE RegisterUser(
    IN p_username VARCHAR(50),
    IN p_age INT,
    IN p_email VARCHAR(100),
    IN p_password VARCHAR(255)
)
BEGIN
    DECLARE username_exists INT DEFAULT 0;
    DECLARE email_exists INT DEFAULT 0;
    
    SELECT COUNT(*) INTO username_exists FROM users WHERE username = p_username;
    SELECT COUNT(*) INTO email_exists FROM users WHERE email = p_email;
    
    IF username_exists > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Username already exists';
    ELSEIF email_exists > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Email already exists';
    ELSE
        INSERT INTO users (username, age, email, password) 
        VALUES (p_username, p_age, p_email, p_password);
        SELECT 'User registered successfully' AS message;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetUserByUsername(
    IN p_username VARCHAR(50)
)
BEGIN
    SELECT id, username, age, email, created_at, updated_at
    FROM users 
    WHERE username = p_username;
END //
DELIMITER ;

DELIMITER //
CREATE FUNCTION IsValidEmail(email VARCHAR(100)) 
RETURNS BOOLEAN
READS SQL DATA
DETERMINISTIC
BEGIN
    RETURN email REGEXP '^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$';
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER validate_user_data
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    IF CHAR_LENGTH(TRIM(NEW.username)) < 3 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Username must be at least 3 characters long';
    END IF;

    IF NEW.age < 18 OR NEW.age > 60 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Age must be between 18 and 60';
    END IF;
    
    IF NOT IsValidEmail(NEW.email) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid email format';
    END IF;

    IF CHAR_LENGTH(NEW.password) < 6 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Password must be at least 6 characters long';
    END IF;

    SET NEW.username = TRIM(NEW.username);
    SET NEW.email = TRIM(LOWER(NEW.email));
END //
DELIMITER ;

select * from users;