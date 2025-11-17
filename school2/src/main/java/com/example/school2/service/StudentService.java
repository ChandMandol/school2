package com.example.school2.service;

import com.example.school2.entity.Student;
import com.example.school2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    // Save or update student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Delete student by ID
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    // Get student by studentId
    public Student getStudentByStudentId(String studentId) {
        Optional<Student> student = studentRepository.findByStudentId(studentId);
        return student.orElse(null);
    }
}