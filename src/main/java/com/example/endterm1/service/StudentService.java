package com.example.endterm1.service;

import com.example.endterm1.model.Student;
import com.example.endterm1.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.endterm1.cache.StudentCache;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {

        StudentCache cache = StudentCache.getInstance();

        if (cache.contains("allStudents")) {
            return cache.get("allStudents");
        }

        List<Student> students = studentRepository.findAll();
        cache.put("allStudents", students);

        return students;
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        Student saved = studentRepository.save(student);
        StudentCache.getInstance().clear();
        return saved;
    }

    public Student updateStudent(int id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());

            Student saved = studentRepository.save(student);

            StudentCache.getInstance().clear();

            return saved;
        }).orElse(null);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
        StudentCache.getInstance().clear();
    }
}
