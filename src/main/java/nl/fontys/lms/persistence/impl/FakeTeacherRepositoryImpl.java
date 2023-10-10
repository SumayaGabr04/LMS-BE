package nl.fontys.lms.persistence.impl;

import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FakeTeacherRepositoryImpl implements TeacherRepository {
    private static long NEXT_TEACHER_ID = 1;
    private final ArrayList<TeacherEntity> teachers;

    public FakeTeacherRepositoryImpl() {
        this.teachers = new ArrayList<>();
    }

    @Override
    public boolean existsById(long teacherId) {
        return teachers.stream().anyMatch(teacherEntity -> teacherEntity.getTeacherId() == teacherId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return teachers.stream().anyMatch(studentEntity -> studentEntity.getEmail().equals(email));
    }


    @Override
    public void deleteById(long teacherId) {
        teachers.removeIf(teacherEntity -> teacherEntity.getTeacherId() == teacherId);
    }

    @Override
    public TeacherEntity findById(long teacherId) {
        return teachers.stream().filter(teacherEntity -> teacherEntity.getTeacherId() == teacherId).findFirst().orElse(null);
    }

    @Override
    public ArrayList<TeacherEntity> findAll() {
        return new ArrayList<>(teachers);
    }

    @Override
    public TeacherEntity save(TeacherEntity teacher) {
        if (teacher.getTeacherId() == null) {
            teacher.setTeacherId(NEXT_TEACHER_ID);
            NEXT_TEACHER_ID++;
        }
        teachers.add(teacher);
        return teacher;
    }

    @Override
    public int count() {
        return teachers.size();
    }

    @Override
    public ArrayList<CourseEntity> getCoursesTaughtByTeacher(long teacherId) {
        TeacherEntity teacher = findById(teacherId);
        if (teacher != null && teacher.getCoursesTaught() != null) {
            return new ArrayList<>(teacher.getCoursesTaught());
        }
        return new ArrayList<>();
    }
}
