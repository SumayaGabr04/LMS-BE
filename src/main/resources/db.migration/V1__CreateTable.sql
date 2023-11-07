CREATE TABLE course (
    id INT NOT NULL AUTO_INCREMENT,
    courseName VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    enrollmentCapacity INT NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    material VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);
