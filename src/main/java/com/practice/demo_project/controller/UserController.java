package com.practice.demo_project.controller;

import com.practice.demo_project.model.User;
import com.practice.demo_project.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return service.getUserById(id);
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id){
        return service.deleteUserById(id);
    }
}
