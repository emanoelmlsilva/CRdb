package com.mini_projeto.crdb.dtos;

import com.mini_projeto.crdb.models.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "email" })
public class UserDTO {

    private String name;

    private String lastName;

    private String email;

    public UserDTO(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}