package com.practice.demo_project.repository;

import com.practice.demo_project.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> users;
    private int lastGeneratedId;
    @PostConstruct
    public void init(){
        this.users = new ArrayList<>();
        this.users.add(new User(1, "Mainak", "mainak@email.com"));
        this.users.add(new User(2, "Aarav", "aarav@email.com"));
        this.users.add(new User(3, "Sinchana", "sinchana@email.com"));
        this.users.add(new User(4, "Rohan", "rohan@email.com"));
        this.users.add(new User(5, "Sneha", "sneha@email.com"));
        this.users.add(new User(6, "Kabir", "kabir@email.com"));
        this.users.add(new User(7, "Ananya", "ananya@email.com"));
        this.users.add(new User(8, "Vivaan", "vivaan@email.com"));
        this.users.add(new User(9, "Meera", "meera@email.com"));
        this.users.add(new User(10, "Aditya", "aditya@email.com"));

        this.lastGeneratedId = 10;
    }

    public List<User> findAll(){
        return this.users;
    }

    public Optional<User> findById(int id){
        List<User> theUsers = this.users.stream().filter(user -> user.getId() == id).toList();

        if(users.isEmpty())
            return Optional.empty();
        else
            return Optional.of(theUsers.getFirst());
    }

    public User save(User user){
        // Save new
        if(user.getId() == 0){
            user.setId(++this.lastGeneratedId);
        }else{  // Update existing
            List<User> toBeUpdatedUserList = this.users.stream().filter(u -> u.getId() == user.getId()).toList();

            if(toBeUpdatedUserList.isEmpty()){
                return null;
            }
            User toBeUpdatedUser = toBeUpdatedUserList.getFirst();
            this.users.remove(toBeUpdatedUser);

        }
        this.users.add(user);
        return user;
    }

    public boolean delete(User user){
        if(users.contains(user)){
            this.users.remove(user);
            return true;
        }
        return false;
    }

    public boolean deleteById(int id){
        List<User> toBeDeltedList = this.users.stream().filter(user -> user.getId() == id).toList();

        if(toBeDeltedList.isEmpty())
            return false;

        this.users.remove(toBeDeltedList.getFirst());
        return true;
    }
}
