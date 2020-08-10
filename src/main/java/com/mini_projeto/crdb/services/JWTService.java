package com.mini_projeto.crdb.services;

import java.util.Date;
import java.util.Optional;

import com.mini_projeto.crdb.Filter.TokenFilter;
import com.mini_projeto.crdb.dtos.UserLoginDTO;
import com.mini_projeto.crdb.models.User;
import com.mini_projeto.crdb.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {

    public static final String TOKEN_KEY = "oden";

    @Autowired
    private UserRepository userRepository;

    public LoginResponse authentication(UserLoginDTO userLoginDTO) {

        String messageErro = "Usuario e/ou senha invalido(s). Login nao realizado";
        Optional<User> optionalUser = userRepository.findByEmail(userLoginDTO.getEmail());

        if (optionalUser.isPresent() && userLoginDTO.getPassword().equals(optionalUser.get().getPassword())) {
            return new LoginResponse(generateToken(userLoginDTO));
        }

        return new LoginResponse(messageErro);
    }

    private String generateToken(UserLoginDTO userLoginDTO) {

        String token = Jwts.builder().setSubject(userLoginDTO.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();// 3 min

        return token;

    }

    public Optional<String> recoverUser(String headerAuthorization) {

        if (headerAuthorization == null || !headerAuthorization.startsWith("Bearer ")) {
            throw new SecurityException("Token inexistente ou mal formatado!");
        }

        // Extraindo apenas o token do cabecalho.
        String token = headerAuthorization.substring(TokenFilter.TOKEN_INDEX);

        String subject = "";

        try {

            subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();

        } catch (Exception erro) {
            throw new SecurityException("Token invalido ou expirado!");
        }

        return Optional.of(subject);

    }

    public String getUserEmail(String authentication) {

        Optional<String> emailUser = recoverUser(authentication);

        if (emailUser.isEmpty()) {
            throw new SecurityException();
        }

        return emailUser.get();
    }
}