package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Category;
import com.example.lesson11task.entity.Client;
import com.example.lesson11task.repository.CategoryRepository;
import com.example.lesson11task.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;


    @GetMapping
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getOne(@PathVariable Integer id) {
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Client();
    }


    @PostMapping
    public String addClient(@RequestBody Client client) {
        boolean b = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (b) return "this client already exists";
        Client client1 = new Client();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client1);
        return "client added";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable Integer id) {
        if (!clientRepository.existsById(id)) {
            return "try again";
        }
        clientRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateCleint(@PathVariable Integer id, @RequestBody Client client) {
        if (!clientRepository.existsById(id) || clientRepository.existsByPhoneNumberAndIdNot(client.getPhoneNumber(), id)) {
            return "try again";
        }
        Client client1 = clientRepository.findById(id).get();
        client1.setPhoneNumber(client.getPhoneNumber());
        client1.setName(client.getName());
        clientRepository.save(client1);
        return "client updated";
    }

}
