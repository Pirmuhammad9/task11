package com.example.lesson11task.repository;

import com.example.lesson11task.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputRepository extends JpaRepository<Input, Integer> {
}
