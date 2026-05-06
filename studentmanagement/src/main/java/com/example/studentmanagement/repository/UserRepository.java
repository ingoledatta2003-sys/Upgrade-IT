package com.example.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentmanagement.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}