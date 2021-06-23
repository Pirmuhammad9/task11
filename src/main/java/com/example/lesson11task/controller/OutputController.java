package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Input;
import com.example.lesson11task.entity.Output;
import com.example.lesson11task.payload.InputDto;
import com.example.lesson11task.payload.OutputDto;
import com.example.lesson11task.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/output")
public class OutputController {

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @GetMapping
    public List<Output> getAll() {
        return outputRepository.findAll();
    }

    @GetMapping("/{id}")
    public Output getOne(@PathVariable Integer id) {
        Optional<Output> byId = outputRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Output();
    }


    @PostMapping
    public String addOutput(@RequestBody OutputDto outputDto) {
        boolean b = currencyRepository.existsById(outputDto.getCurrencyId());
        boolean b1 = clientRepository.existsById(outputDto.getClientId());
        boolean b2 = warehouseRepository.existsById(outputDto.getWarehouseId());
        if (!b || !b1 || !b2) return "try again";
        Output output = new Output();
        output.setCode(outputDto.getCode());
        output.setCurrency(currencyRepository.getById(outputDto.getCurrencyId()));
        output.setDate(outputDto.getDate());
        output.setFactureNumber(output.getFactureNumber());
        output.setClient(clientRepository.getById(outputDto.getClientId()));
        output.setWarehouse(warehouseRepository.getById(outputDto.getWarehouseId()));
        outputRepository.save(output);
        return "added";
    }

    @DeleteMapping("/{id}")
    public String deleteOutput(@PathVariable Integer id) {
        if (!outputRepository.existsById(id)) {
            return "try again";
        }
        outputRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateOutput(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        if (!outputRepository.existsById(id)
                || !clientRepository.existsById(outputDto.getClientId())
                || !warehouseRepository.existsById(outputDto.getWarehouseId())
                || !currencyRepository.existsById(outputDto.getCurrencyId())) {
            return "try again";
        }

        Output output = outputRepository.getById(id);
        output.setCode(outputDto.getCode());
        output.setCurrency(currencyRepository.getById(outputDto.getCurrencyId()));
        output.setDate(outputDto.getDate());
        output.setFactureNumber(output.getFactureNumber());
        output.setClient(clientRepository.getById(outputDto.getClientId()));
        output.setWarehouse(warehouseRepository.getById(outputDto.getWarehouseId()));
        outputRepository.save(output);
        return "output updated";
    }

}
