package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//@Repository
public interface TeacherRepository {
    boolean existsById(long teacherId);

    boolean existsByEmail(String email);

    void deleteById(long teacherId);

    TeacherEntity findById(long teacherId);

    ArrayList<TeacherEntity> findAll();

    TeacherEntity save(TeacherEntity teacher);

    int count();
}
