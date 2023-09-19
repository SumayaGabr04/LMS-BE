package nl.fontys.lms.persistence.impl;

import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FakeStudentRepositoryImpl implements StudentRepository {
    private static long NEXT_STUDENT_ID = 1;
    private final ArrayList<StudentEntity> students;

    public FakeStudentRepositoryImpl() {
        this.students = new ArrayList<>();
    }

    @Override
    public boolean existsById(long studentId) {
        return students.stream().anyMatch(studentEntity -> studentEntity.getStudentId() == studentId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return students.stream().anyMatch(studentEntity -> studentEntity.getEmail().equals(email));
    }


    @Override
    public void deleteById(long studentId) {
        students.removeIf(studentEntity -> studentEntity.getStudentId() == studentId);
    }

    @Override
    public StudentEntity findById(long studentId) {
        return students.stream().filter(studentEntity -> studentEntity.getStudentId() == studentId).findFirst().orElse(null);
    }

    @Override
    public ArrayList<StudentEntity> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public StudentEntity save(StudentEntity student) {
        if (student.getStudentId() == null) {
            student.setStudentId(NEXT_STUDENT_ID);
            NEXT_STUDENT_ID++;
        }
        students.add(student);
        return student;
    }

    @Override
    public int count() {
        return students.size();
    }
}
