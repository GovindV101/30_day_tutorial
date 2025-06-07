-- Table for Advisors
CREATE TABLE Advisors (
    advisor_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    specialization VARCHAR(100) NOT NULL
);
SHOW TABLES;
DESCRIBE Advisors;
-- Table for Students
CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    date_of_birth DATE NOT NULL,
    advisor_id INT,
    CONSTRAINT fk_advisor FOREIGN KEY (advisor_id) REFERENCES Advisors(advisor_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- Table for Courses
CREATE TABLE Courses (
    course_code VARCHAR(10) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    instructor VARCHAR(100) NOT NULL
);

-- Junction table for Student-Course many-to-many relationship
CREATE TABLE Student_Course (
    student_id INT,
    course_code VARCHAR(10),
    PRIMARY KEY (student_id, course_code),
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES Students(student_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (course_code) REFERENCES Courses(course_code)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
