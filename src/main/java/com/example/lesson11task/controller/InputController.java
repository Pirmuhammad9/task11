package com.example.lesson11task.controller;

import com.example.lesson11task.entity.*;
import com.example.lesson11task.payload.InputDto;
import com.example.lesson11task.payload.InputProductDto;
import com.example.lesson11task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/input")
public class InputController {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @GetMapping
    public List<Input> getAll() {
        return inputRepository.findAll();
    }

    @GetMapping("/{id}")
    public Input getOne(@PathVariable Integer id) {
        Optional<Input> byId = inputRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Input();
    }


    @PostMapping
    public String addInput(@RequestBody InputDto inputDto) {
        boolean b = currencyRepository.existsById(inputDto.getCurrencyId());
        boolean b1 = supplierRepository.existsById(inputDto.getSupplierId());
        boolean b2 = warehouseRepository.existsById(inputDto.getWarehouseId());
        if (!b || !b1 || !b2) return "try again";
        Input input = new Input();
        input.setCode(inputDto.getCode());
        input.setCurrency(currencyRepository.getById(inputDto.getCurrencyId()));
        input.setDate(inputDto.getDate());
        input.setFactoreName(inputDto.getFactoreName());
        input.setSupplier(supplierRepository.getById(inputDto.getSupplierId()));
        input.setWarehouse(warehouseRepository.getById(inputDto.getWarehouseId()));
        inputRepository.save(input);
        return "added";
    }

    @DeleteMapping("/{id}")
    public String deleteInput(@PathVariable Integer id) {
        if (!inputRepository.existsById(id)) {
            return "try again";
        }
        inputRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateInput(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        if (!inputRepository.existsById(id)
                || !supplierRepository.existsById(inputDto.getSupplierId())
                || !warehouseRepository.existsById(inputDto.getWarehouseId())
                || !currencyRepository.existsById(inputDto.getCurrencyId())) {
            return "try again";
        }

        Input input = inputRepository.getById(id);
        input.setCode(inputDto.getCode());
        input.setCurrency(currencyRepository.getById(inputDto.getCurrencyId()));
        input.setDate(inputDto.getDate());
        input.setFactoreName(inputDto.getFactoreName());
        input.setSupplier(supplierRepository.getById(inputDto.getSupplierId()));
        input.setWarehouse(warehouseRepository.getById(inputDto.getWarehouseId()));
        inputRepository.save(input);
        return "input updated";
    }

}
