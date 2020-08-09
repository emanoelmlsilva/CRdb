package com.mini_projeto.crdb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = { "email" })
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "{name.not.blank}")
    private String name;
    @NotBlank(message = "{lastName.not.blank}")
    private String lastName;

    @Id
    @Email(message = "{email.not.blank}")
    private String email;

    @NotBlank(message = "{password.not.blank}")
    private String password;

    public User() {
        super();
    }

    public boolean isValid() {
        return !this.name.isBlank() && !this.lastName.isBlank() && !this.email.isBlank() && !this.password.isBlank();
    }

}