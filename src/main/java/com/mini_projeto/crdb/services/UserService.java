package com.mini_projeto.crdb.services;

import com.mini_projeto.crdb.dtos.UserDTO;
import com.mini_projeto.crdb.exceptions.UserAlreadyExistsException;
import com.mini_projeto.crdb.exceptions.UserInvalidException;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO insert(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        } else if (!user.isValid()) {
            throw new UserInvalidException();
        }

        userRepository.save(user);

        return new UserDTO(user);

    }

}