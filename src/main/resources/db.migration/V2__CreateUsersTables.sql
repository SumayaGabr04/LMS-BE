-- Create the user table
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    major VARCHAR(255), -- Add columns specific to student
    department VARCHAR(255), -- Add columns specific to teacher/admin
    hire_date DATE -- Add columns specific to teacher/admin
);
