package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Client;
import com.example.lesson11task.entity.Currency;
import com.example.lesson11task.repository.ClientRepository;
import com.example.lesson11task.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepository currencyRepository;


    @GetMapping
    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Currency getOne(@PathVariable Integer id) {
        Optional<Currency> byId = currencyRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Currency();
    }


    @PostMapping
    public String addCurrency(@RequestBody Currency currency) {
        boolean b = currencyRepository.existsByName(currency.getName());
        if (b) return "this currency already exists";
        Currency currency1 = new Currency();
        currency1.setName(currency.getName());
        currency1.setActive(currency.isActive());
        currencyRepository.save(currency1);
        return "currency added";
    }

    @DeleteMapping("/{id}")
    public String deleteCurrency(@PathVariable Integer id) {
        if (!currencyRepository.existsById(id)) {
            return "try again";
        }
        currencyRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateCurrency(@PathVariable Integer id, @RequestBody Currency currency) {
        if (!currencyRepository.existsById(id) || currencyRepository.existsByNameAndIdNot(currency.getName(), id)) {
            return "try again";
        }
        Currency currency1 = currencyRepository.findById(id).get();
        currency1.setName(currency.getName());
        currency1.setActive(currency.isActive());
        currencyRepository.save(currency);
        return "currency updated";
    }

}
