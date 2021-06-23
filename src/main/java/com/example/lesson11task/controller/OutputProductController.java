package com.example.lesson11task.controller;

import com.example.lesson11task.entity.OutputProduct;
import com.example.lesson11task.payload.OutputProductDto;
import com.example.lesson11task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/outputProduct")
public class OutputProductController {
    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    @GetMapping
    public List<OutputProduct> getAll() {
        return outputProductRepository.findAll();
    }

    @GetMapping("/{id}")
    public OutputProduct getOne(@PathVariable Integer id) {
        Optional<OutputProduct> byId = outputProductRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new OutputProduct();
    }


    @PostMapping
    public String addOutputProduct(@RequestBody OutputProductDto outputProductDto) {
        boolean b = outputRepository.existsById(outputProductDto.getOutputId());
        boolean b1 = productRepository.existsById(outputProductDto.getProductId());
        if (!b || !b1) return "try again";
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(productRepository.getById(outputProductDto.getProductId()));
        outputProduct.setOutput(outputRepository.getById(outputProductDto.getOutputId()));
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return "added";
    }

    @DeleteMapping("/{id}")
    public String deleteOutputProduct(@PathVariable Integer id) {
        if (!outputProductRepository.existsById(id)) {
            return "try again";
        }
        outputProductRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateOutputProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto) {
        if (!outputProductRepository.existsById(id) || !outputRepository.existsById(outputProductDto.getOutputId()) || !productRepository.existsById(outputProductDto.getProductId())) {
            return "try again";
        }
        OutputProduct outputProduct = outputProductRepository.getById(id);
        outputProduct.setProduct(productRepository.getById(outputProductDto.getProductId()));
        outputProduct.setOutput(outputRepository.getById(outputProductDto.getOutputId()));
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProductRepository.save(outputProduct);
        return "outputProduct updated";
    }

}
