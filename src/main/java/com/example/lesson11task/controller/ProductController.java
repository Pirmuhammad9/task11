package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Measurement;
import com.example.lesson11task.entity.Output;
import com.example.lesson11task.entity.Product;
import com.example.lesson11task.payload.OutputDto;
import com.example.lesson11task.payload.ProductDto;
import com.example.lesson11task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PrinterURI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Product();
    }


    @PostMapping
    public String addProduct(@RequestBody ProductDto productDto) {
        boolean b = categoryRepository.existsById(productDto.getCategoryId());
        boolean b1 = attachmentRepository.existsById(productDto.getAttachmentId());
        boolean b2 = measurementRepository.existsById(productDto.getMeasurementId());
        if (!b || !b1 || !b2) return "try again";
        Product product = new Product();
        product.setCode(productDto.getCode());
        product.setActive(productDto.isActive());
        product.setAttachment(attachmentRepository.getById(productDto.getAttachmentId()));
        product.setName(productDto.getName());
        product.setCategory(categoryRepository.getById(productDto.getCategoryId()));
        product.setMeasurement(measurementRepository.getById(productDto.getMeasurementId()));
        productRepository.save(product);
        return "added";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        if (!productRepository.existsById(id)) {
            return "try again";
        }
        productRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        if (!productRepository.existsById(id)
                || !attachmentRepository.existsById(productDto.getAttachmentId())
                || !categoryRepository.existsById(productDto.getCategoryId())
                || !measurementRepository.existsById(productDto.getMeasurementId())) {
            return "try again";
        }

        Product product = productRepository.getById(id);
        product.setCode(productDto.getCode());
        product.setActive(productDto.isActive());
        product.setAttachment(attachmentRepository.getById(productDto.getAttachmentId()));
        product.setName(productDto.getName());
        product.setCategory(categoryRepository.getById(productDto.getCategoryId()));
        product.setMeasurement(measurementRepository.getById(productDto.getMeasurementId()));
        productRepository.save(product);
        return "product updated";
    }

}
