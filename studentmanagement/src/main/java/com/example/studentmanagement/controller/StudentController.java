package com.example.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class StudentController {

    @Autowired
    private StudentRepository repo;

    public Student saveStudent(Student s) {
        return repo.save(s);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student s) {

        Student old = repo.findById(id).orElse(null);

        if (old != null) {
            old.setName(s.getName());
            old.setCourse(s.getCourse());
            old.setEmail(s.getEmail());
            old.setMobileNo(s.getMobileNo());

            return repo.save(old);
        }

        return null;
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}