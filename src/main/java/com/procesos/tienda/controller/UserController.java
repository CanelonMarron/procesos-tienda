package com.procesos.tienda.controller;

import com.procesos.tienda.model.User;
import com.procesos.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping("users")
    public ResponseEntity<User> create(@Validated @RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> update(@Validated @RequestBody User user, @PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(user,id), HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(Boolean.toString(userService.deleteUser(id)), HttpStatus.NO_CONTENT);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

}
