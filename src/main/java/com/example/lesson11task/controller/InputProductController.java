package com.example.lesson11task.controller;

import com.example.lesson11task.entity.InputProduct;
import com.example.lesson11task.payload.InputProductDto;
import com.example.lesson11task.repository.InputProductRepository;
import com.example.lesson11task.repository.InputRepository;
import com.example.lesson11task.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inputProduct")
public class InputProductController {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    @GetMapping
    public List<InputProduct> getAll() {
        return inputProductRepository.findAll();
    }

    @GetMapping("/{id}")
    public InputProduct getOne(@PathVariable Integer id) {
        Optional<InputProduct> byId = inputProductRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new InputProduct();
    }


    @PostMapping
    public String addInputProduct(@RequestBody InputProductDto inputProductDto) {
        boolean b = inputRepository.existsById(inputProductDto.getInputId());
        boolean b1 = productRepository.existsById(inputProductDto.getProductId());
        if (!b || !b1) return "try again";
        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(productRepository.getById(inputProductDto.getProductId()));
        inputProduct.setInput(inputRepository.getById(inputProductDto.getInputId()));
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        return "added";
    }

    @DeleteMapping("/{id}")
    public String deleteInputProduct(@PathVariable Integer id) {
        if (!inputProductRepository.existsById(id)) {
            return "try again";
        }
        inputProductRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        if (!inputProductRepository.existsById(id) || !inputRepository.existsById(inputProductDto.getInputId()) || !productRepository.existsById(inputProductDto.getProductId())) {
            return "try again";
        }

        InputProduct byId = inputProductRepository.getById(id);
        byId.setProduct(productRepository.getById(inputProductDto.getProductId()));
        byId.setInput(inputRepository.getById(inputProductDto.getInputId()));
        byId.setPrice(inputProductDto.getPrice());
        byId.setAmount(inputProductDto.getAmount());
        byId.setExpireDate(inputProductDto.getExpireDate());

        return "inputProduct updated";
    }

}
