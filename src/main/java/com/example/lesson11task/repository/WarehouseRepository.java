package com.example.lesson11task.repository;

import com.example.lesson11task.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);

}
