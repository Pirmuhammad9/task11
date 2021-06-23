package com.example.lesson11task.repository;

import com.example.lesson11task.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
