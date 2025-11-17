package com.example.school2.controller;

import com.example.school2.entity.Student;
import com.example.school2.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Display the home page with admin and student sections
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Display all students for admin (with CRUD operations)
    @GetMapping("/admin/students")
    public String listStudentsAdmin(HttpSession session, Model model) {
        // Check if admin is authenticated
        if (session.getAttribute("adminAuthenticated") == null) {
            return "redirect:/admin/login";
        }
        
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students_admin";
    }

    // Display all students for students (view only)
    @GetMapping("/students")
    public String listStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    // Show form for adding a new student (admin only)
    @GetMapping("/admin/students/new")
    public String createStudentForm(HttpSession session, Model model) {
        // Check if admin is authenticated
        if (session.getAttribute("adminAuthenticated") == null) {
            return "redirect:/admin/login";
        }
        
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";
    }

    // Save a new student (admin only)
    @PostMapping("/admin/students")
    public String saveStudent(@ModelAttribute("student") Student student, HttpSession session) {
        // Check if admin is authenticated
        if (session.getAttribute("adminAuthenticated") == null) {
            return "redirect:/admin/login";
        }
        
        studentService.saveStudent(student);
        return "redirect:/admin/students";
    }

    // Show form for editing a student (admin only)
    @GetMapping("/admin/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, HttpSession session, Model model) {
        // Check if admin is authenticated
        if (session.getAttribute("adminAuthenticated") == null) {
            return "redirect:/admin/login";
        }
        
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "edit_student";
        } else {
            return "redirect:/admin/students";
        }
    }

    // Update a student (admin only)
    @PostMapping("/admin/students/{id}")
    public String updateStudent(@PathVariable Long id,
                               @ModelAttribute("student") Student student,
                               HttpSession session) {
        // Check if admin is authenticated
        if (session.getAttribute("adminAuthenticated") == null) {
            return "redirect:/admin/login";
        }
                               
        // Get the existing student from the database
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            // Update the existing student with new values
            existingStudent.setName(student.getName());
            existingStudent.setStudentId(student.getStudentId());
            existingStudent.setCourse(student.getCourse());
            existingStudent.setGrade(student.getGrade());

            // Save the updated student
            studentService.saveStudent(existingStudent);
        }
        return "redirect:/admin/students";
    }

    // Delete a student (admin only)
    @GetMapping("/admin/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        // Check if admin is authenticated
        if (session.getAttribute("adminAuthenticated") == null) {
            return "redirect:/admin/login";
        }
        
        studentService.deleteStudentById(id);
        return "redirect:/admin/students";
    }
}