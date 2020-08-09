package com.mini_projeto.crdb.controllers;

import com.mini_projeto.crdb.dtos.UserLoginDTO;
import com.mini_projeto.crdb.services.JWTService;
import com.mini_projeto.crdb.services.LoginResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/auth")
public class LoginController {

    @Autowired
    private JWTService jwtService;

    @PostMapping("")
    public ResponseEntity<LoginResponse> authentication(@RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<LoginResponse>(jwtService.authentication(userLoginDTO), HttpStatus.OK);
    }

}