package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Category;
import com.example.lesson11task.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getOne(@PathVariable Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Category();
    }


    @PostMapping
    public String addCategory(@RequestBody Category category) {
        boolean b = categoryRepository.existsByName(category.getName());
        if (b) return "this category already exists";
        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setActive(category.isActive());
        categoryRepository.save(category1);
        return "category added";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        if (!categoryRepository.existsById(id)) {
            return "try again";
        }
        categoryRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateAddress(@PathVariable Integer id, @RequestBody Category category) {
        if (!categoryRepository.existsById(id) || categoryRepository.existsByNameAndIdNot(category.getName(), id)) {
            return "try again";
        }
        Category category1 = categoryRepository.findById(id).get();
        category1.setActive(category.isActive());
        category1.setName(category.getName());
        categoryRepository.save(category1);
        return "category updated";
    }

}
