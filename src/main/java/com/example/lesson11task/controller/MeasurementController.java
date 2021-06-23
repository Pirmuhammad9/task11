package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Measurement;
import com.example.lesson11task.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {
    @Autowired
    MeasurementRepository measurementRepository;


    @GetMapping
    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Measurement getOne(@PathVariable Integer id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Measurement();
    }


    @PostMapping
    public String addMeasurement(@RequestBody Measurement measurement) {
        boolean b = measurementRepository.existsByName(measurement.getName());
        if (b) return "this measurement already exists";
        Measurement measurement1 = new Measurement();
        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());
        measurementRepository.save(measurement);
        return "measurement added";
    }

    @DeleteMapping("/{id}")
    public String deleteMeasurement(@PathVariable Integer id) {
        if (!measurementRepository.existsById(id)) {
            return "try again";
        }
        measurementRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement) {
        if (!measurementRepository.existsById(id) || measurementRepository.existsByNameAndIdNot(measurement.getName(), id)) {
            return "try again";
        }
        Measurement measurement1 = measurementRepository.findById(id).get();
        measurement1.setActive(measurement.isActive());
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return "measurement updated";
    }

}
