package com.practice.demo_project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demo_project.controller.UserController;
import com.practice.demo_project.exception.UserException;
import com.practice.demo_project.model.User;
import com.practice.demo_project.services.UserService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = List.of(
                new User(1, "Mainak", "m@email.com"),
                new User(2, "Cristiano", "c@email.com")
        );

        when(service.getAllUsers()).thenReturn(ResponseEntity.status(HttpStatus.OK).body(users));

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mainak"));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1, "Mainak", "m@email.com");

        when(service.getUserById(1)).thenReturn(
                ResponseEntity.status(HttpStatus.OK).body(user)
        );

//        when(service.getUserById(2)).thenThrow(new UserException("No user found with id: " + 2, HttpStatus.BAD_REQUEST));

        mockMvc.perform(get("/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mainak"));
    }

    @Test
    public void testSaveUser() throws Exception {
        User user = new User(1, "Mainak", "m@email.com");

        when(service.saveUser(user)).thenReturn(
                ResponseEntity.status(HttpStatus.CREATED).body(user)
        );

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Mainak"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User(1, "Mainak", "m@email.com");

        when(service.updateUser(user)).thenReturn(
                ResponseEntity.status(HttpStatus.OK).body(user)
        );

        mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mainak"));
    }
}
