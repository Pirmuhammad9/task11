package com.example.lesson11task.controller;

import com.example.lesson11task.entity.Users;
import com.example.lesson11task.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UsersController {
    @Autowired
    UsersRepository usersRepository;


    @GetMapping
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getOne(@PathVariable Integer id) {
        Optional<Users> byId = usersRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Users();
    }


    @PostMapping
    public String addUser(@RequestBody Users users) {
        boolean b = usersRepository.existsByPhoneNumber(users.getPhoneNumber());
        if (b) return "this user already exists";
        Users users1 = new Users();
        users1.setActive(users.isActive());
        users1.setCode(users.getCode());
        users1.setFirstname(users.getFirstname());
        users1.setLastname(users.getLastname());
        users1.setPassword(users.getPassword());
        users1.setPhoneNumber(users.getPhoneNumber());
        usersRepository.save(users1);
        return "user added";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        if (!usersRepository.existsById(id)) {
            return "try again";
        }
        usersRepository.deleteById(id);
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody Users users) {
        if (!usersRepository.existsById(id) || usersRepository.existsByPhoneNumberAndIdNot(users.getPhoneNumber(), id)) {
            return "try again";
        }
        Users users1 = usersRepository.getById(id);
        users1.setActive(users.isActive());
        users1.setCode(users.getCode());
        users1.setFirstname(users.getFirstname());
        users1.setLastname(users.getLastname());
        users1.setPassword(users.getPassword());
        users1.setPhoneNumber(users.getPhoneNumber());
        usersRepository.save(users1);
        return "User updated";
    }

}
