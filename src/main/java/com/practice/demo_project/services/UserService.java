package com.practice.demo_project.services;

import com.practice.demo_project.exception.UserException;
import com.practice.demo_project.model.User;
import com.practice.demo_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repo;

    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = repo.findAll();
        if(users.isEmpty()){
            throw new UserException("No users found!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity<User> getUserById(int id){
        User user = repo.findById(id).orElse(null);
        if(user == null){
            throw new UserException("No user found with id: " + id, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public ResponseEntity<User> saveUser(User user){
        user.setId(0);
        User savedUser = repo.save(user);
        if(savedUser == null){
            throw new UserException("Failed to save the user!", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    public ResponseEntity<User> updateUser(User user){
        User updatedUser = repo.save(user);
        if(updatedUser == null){
            throw new UserException("No user found with id: " + user.getId(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    public ResponseEntity<Void> deleteUserById(int id){
        boolean result = repo.deleteById(id);

        if(!result){
            throw new UserException("No user found with id: " + id, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
