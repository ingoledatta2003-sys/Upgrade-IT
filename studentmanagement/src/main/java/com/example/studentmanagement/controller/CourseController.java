package com.example.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class CourseController {

    @GetMapping("/course-schedule")
    public String courseSchedule(Model model) {

        Map<String, String> schedule = new LinkedHashMap<>();

        schedule.put("Java Full Stack", "9:00 AM - 10:30 AM");
        schedule.put("Python", "10:30 AM - 12:00 PM");
        schedule.put("C Programming", "12:00 PM - 1:00 PM");
        schedule.put("C++", "2:00 PM - 3:00 PM");
        schedule.put("Data Science", "3:00 PM - 4:30 PM");
        schedule.put("Data Analytics", "4:30 PM - 5:30 PM");
        schedule.put("DBMS", "9:00 AM - 10:00 AM");
        schedule.put("Cloud Computing", "10:00 AM - 11:00 AM");
        schedule.put("DevOps", "11:00 AM - 12:00 PM");
        schedule.put("Web Development", "1:00 PM - 2:00 PM");
        schedule.put("AI", "5:30 PM - 6:30 PM");
        schedule.put("Machine Learning", "6:30 PM - 7:30 PM");

        model.addAttribute("schedule", schedule);

        return "course-schedule";
    }
}