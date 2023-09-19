package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.StudentEntity;

import java.util.ArrayList;

public interface StudentRepository {
    boolean existsById(long studentId);

    boolean existsByEmail(String email);

    void deleteById(long studentId);

    StudentEntity findById(long studentId);

    ArrayList<StudentEntity> findAll();

    StudentEntity save(StudentEntity student);

    int count();
}
