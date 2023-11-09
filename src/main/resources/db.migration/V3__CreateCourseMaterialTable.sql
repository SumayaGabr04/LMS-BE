CREATE TABLE course_materials (
    id INT NOT NULL AUTO_INCREMENT,
    course_id INT NOT NULL,
    material LONGBLOB NULL, -- Use LONGBLOB for binary data
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES course (id)
);
