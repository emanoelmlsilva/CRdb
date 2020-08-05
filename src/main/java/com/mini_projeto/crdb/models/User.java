package com.mini_projeto.crdb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = { "email" })
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String lastName;

    @Id
    private String email;
    private String password;

}