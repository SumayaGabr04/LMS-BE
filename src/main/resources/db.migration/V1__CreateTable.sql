CREATE TABLE course (
    id INT NOT NULL AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    enrollment_capacity INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    material VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
