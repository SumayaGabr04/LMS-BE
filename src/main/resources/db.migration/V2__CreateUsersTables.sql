CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    role VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password_hash VARCHAR(100) NOT NULL,
    password_salt VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE admin (
    id INT NOT NULL,
    department VARCHAR(50),
    hire_date DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user (id)
);

CREATE TABLE teacher (
    id INT NOT NULL,
    department VARCHAR(50),
    hire_date DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user (id)
);

CREATE TABLE student (
    id INT NOT NULL,
    major VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user (id)
);



