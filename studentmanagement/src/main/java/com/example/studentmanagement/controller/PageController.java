package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private StudentService service;

    // =========================
    // DASHBOARD
    // =========================
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Student> allStudents = service.getAllStudents();

        List<Student> registeredStudents = allStudents.stream()
                .filter(Student::isRegistered)
                .toList();

        List<Student> unregisteredStudents = allStudents.stream()
                .filter(s -> !s.isRegistered())
                .toList();

        model.addAttribute("students", unregisteredStudents);
        model.addAttribute("registeredStudents", registeredStudents);

        model.addAttribute("registeredCount", registeredStudents.size());
        model.addAttribute("totalStudents", allStudents.size());

        return "dashboard";
    }

    // =========================
    // ADD STUDENT
    // =========================
    @PostMapping("/saveStudent")
    public String saveStudent(Student student) {

        student.setRegistered(false);
        service.saveStudent(student);

        return "redirect:/dashboard";
    }

    // =========================
    // REGISTER STUDENT (ONLY ONE METHOD)
    // =========================
    @GetMapping("/registerStudent/{id}")
    public String registerStudent(@PathVariable Long id) {

        Student student = service.getStudentById(id);

        if (student != null) {
            student.setRegistered(true);
            service.saveStudent(student);
        }

        return "redirect:/dashboard";
    }

    // =========================
    // UNREGISTER STUDENT
    // =========================
    @GetMapping("/unregisterStudent/{id}")
    public String unregisterStudent(@PathVariable Long id) {

        Student student = service.getStudentById(id);

        if (student != null) {
            student.setRegistered(false);
            service.saveStudent(student);
        }

        return "redirect:/registered-students";
    }

    // =========================
    // DELETE STUDENT
    // =========================
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id) {

        service.deleteStudent(id);

        return "redirect:/dashboard";
    }

    // =========================
    // ALL STUDENTS + SEARCH
    // =========================
    @GetMapping("/students")
    public String showStudents(@RequestParam(required = false) String keyword,
                               Model model) {

        List<Student> students = service.getAllStudents()
                .stream()
                .filter(s -> !s.isRegistered())
                .toList();

        if (keyword != null && !keyword.trim().isEmpty()) {

            String search = keyword.toLowerCase();

            students = students.stream()
                    .filter(s ->
                            (s.getName() != null && s.getName().toLowerCase().contains(search)) ||
                            (s.getCourse() != null && s.getCourse().toLowerCase().contains(search)) ||
                            (s.getEmail() != null && s.getEmail().toLowerCase().contains(search)) ||
                            (s.getMobileNo() != null && s.getMobileNo().contains(search))
                    )
                    .toList();
        }

        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword);

        return "students";
    }

    // =========================
    // REGISTERED STUDENTS PAGE
    // =========================
    @GetMapping("/registered-students")
    public String showRegisteredStudents(Model model) {

        List<Student> students = service.getAllStudents()
                .stream()
                .filter(Student::isRegistered)
                .toList();

        model.addAttribute("students", students);

        return "registered-students";
    }

    // =========================
    // COURSES PAGE
    // =========================
    @GetMapping("/courses")
    public String showCourses(Model model) {

        List<String> courses = List.of(
                "Java Full Stack","Python","C Programming","C++",
                "Data Science","Data Analytics","Database Management",
                "Cloud Computing","DevOps","Web Development",
                "Artificial Intelligence","Machine Learning"
        );

        model.addAttribute("courses", courses);

        return "courses";
    }

    // =========================
    // COURSE WISE STUDENTS
    // =========================
    @GetMapping("/courses/{courseName}")
    public String studentsByCourse(@PathVariable String courseName, Model model) {

        List<Student> students = service.getAllStudents()
                .stream()
                .filter(s -> s.getCourse() != null &&
                        s.getCourse().equalsIgnoreCase(courseName))
                .toList();

        model.addAttribute("students", students);
        model.addAttribute("courseName", courseName);

        return "students";
    }
}