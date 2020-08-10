package com.mini_projeto.crdb.controllers;

import com.mini_projeto.crdb.dtos.UserDTO;
import com.mini_projeto.crdb.dtos.UserUpdateDTO;
import com.mini_projeto.crdb.exceptions.UserAlreadyExistsException;
import com.mini_projeto.crdb.exceptions.UserInvalidException;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> insert(@RequestBody User user) {

        try {
            return new ResponseEntity<UserDTO>(userService.insert(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException existErro) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UserInvalidException invalidErro) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/auth/user")
    public ResponseEntity<UserDTO> delete(@RequestHeader("Authorization") String token) {

        try {
            return new ResponseEntity<UserDTO>(userService.delete(token), HttpStatus.OK);
        } catch (UserInvalidException erro) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping("/auth/user")
    public ResponseEntity<UserUpdateDTO> update(@RequestHeader("Authorization") String token,
            @RequestBody UserUpdateDTO user) {
        try {
            return new ResponseEntity<UserUpdateDTO>(userService.update(user, token), HttpStatus.OK);
        } catch (UserInvalidException erro) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}