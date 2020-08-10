package com.mini_projeto.crdb.dtos;

import com.mini_projeto.crdb.models.User;

import lombok.Data;

@Data
public class UserUpdateDTO {

    private String name;

    private String lastName;

    private String password;

    public UserUpdateDTO() {
        super();
    }

    public UserUpdateDTO(User user) {
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
    }

}