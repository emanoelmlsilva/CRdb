package com.mini_projeto.crdb.Utils;

import java.util.Optional;

import com.mini_projeto.crdb.exceptions.UserInvalidException;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ValidateUser {

    @Autowired
    private UserRepository userRepository;

    public ValidateUser() {

    }

    public User validateUser(Optional<String> email) {
        if (email.isEmpty()) {
            throw new UserInvalidException("Email não existe!");
        }

        Optional<User> user = userRepository.findByEmail(email.get());

        if (user.isEmpty()) {
            throw new UserInvalidException("Usuario não existe");
        }

        return user.get();
    }

}