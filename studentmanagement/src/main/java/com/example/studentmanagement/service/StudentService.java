// StudentService.java

package com.example.studentmanagement.service;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // existing methods...
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public void saveStudent(Student student) {
        repo.save(student);
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }

    public long getRegisteredStudentsCount() {
        return repo.countByRegisteredTrue();
    }

    // ✅ ADD THIS METHOD (FIX)
    public Student getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }
}