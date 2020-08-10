package com.mini_projeto.crdb.services;

import java.util.Optional;

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

    @Autowired
    private JWTService jwtService;

    public UserDTO insert(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        } else if (!user.isValid()) {
            throw new UserInvalidException();
        }

        userRepository.save(user);

        return new UserDTO(user);

    }

    public UserDTO delete(String headerAuthorization) {

        // ler o token e recuperar o subject
        Optional<String> userEmail = jwtService.recoverUser(headerAuthorization);

        // verifica se existe um usuario correspondende ao token
        User user = validateUser(userEmail);

        // remover usuario recuperado
        userRepository.delete(user);

        return new UserDTO(user);
    }

    private User validateUser(Optional<String> email) {
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