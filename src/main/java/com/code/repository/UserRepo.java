package com.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.entities.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {
 public User findByEmail(String email);
}
