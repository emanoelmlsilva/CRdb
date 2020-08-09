package com.mini_projeto.crdb.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = { "email" })
public class UserLoginDTO {

    private String email;
    private String password;
}