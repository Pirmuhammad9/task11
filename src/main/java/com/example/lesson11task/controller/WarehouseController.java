package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Category;
import com.example.lesson11task.entity.Warehouse;
import com.example.lesson11task.repository.CategoryRepository;
import com.example.lesson11task.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseRepository warehouseRepository;


    @GetMapping
    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Warehouse getOne(@PathVariable Integer id) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Warehouse();
    }


    @PostMapping
    public String addWarehouse(@RequestBody Warehouse warehouse) {
        boolean b = warehouseRepository.existsByName(warehouse.getName());
        if (b) return "this warehouse already exists";
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setName(warehouse.getName());
        warehouse1.setActive(warehouse.isActive());
        warehouseRepository.save(warehouse1);
        return "warehouse added";
    }

    @DeleteMapping("/{id}")
    public String deleteWarehouse(@PathVariable Integer id) {
        if (!warehouseRepository.existsById(id)) {
            return "try again";
        }
        warehouseRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse) {
        if (!warehouseRepository.existsById(id) || warehouseRepository.existsByNameAndIdNot(warehouse.getName(), id)) {
            return "try again";
        }
        Warehouse warehouse1 = warehouseRepository.getById(id);
        warehouse1.setName(warehouse.getName());
        warehouse1.setActive(warehouse.isActive());
        warehouseRepository.save(warehouse1);
        return "warehouse updated";
    }

}
