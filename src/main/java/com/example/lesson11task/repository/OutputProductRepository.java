package com.example.lesson11task.repository;

import com.example.lesson11task.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
}
