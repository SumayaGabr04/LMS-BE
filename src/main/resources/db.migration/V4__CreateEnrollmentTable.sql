-- Create the enrollment table
CREATE TABLE enrollment (
    id INT NOT NULL AUTO_INCREMENT,
    course_id INT,
    user_id INT,
    enrollment_date DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES course (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
);
