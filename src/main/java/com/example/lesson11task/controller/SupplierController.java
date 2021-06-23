package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Category;
import com.example.lesson11task.entity.Supplier;
import com.example.lesson11task.repository.CategoryRepository;
import com.example.lesson11task.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    SupplierRepository supplierRepository;


    @GetMapping
    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    @GetMapping("/{id}")
    public Supplier getOne(@PathVariable Integer id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Supplier();
    }


    @PostMapping
    public String addSupplier(@RequestBody Supplier supplier) {
        boolean b = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (b) return "this supplier already exists";
        Supplier supplier1 = new Supplier();
        supplier1.setName(supplier.getName());
        supplier1.setActive(supplier.isActive());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return "supplier added";
    }

    @DeleteMapping("/{id}")
    public String deleteSupplier(@PathVariable Integer id) {
        if (!supplierRepository.existsById(id)) {
            return "try again";
        }
        supplierRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateSupplier(@PathVariable Integer id, @RequestBody Supplier supplier) {
        if (!supplierRepository.existsById(id) || supplierRepository.existsByPhoneNumberAndIdNot(supplier.getPhoneNumber(), id)) {
            return "try again";
        }
        Supplier supplier1 = supplierRepository.getById(id);
        supplier1.setName(supplier.getName());
        supplier1.setActive(supplier.isActive());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return "supplier updated";
    }

}
