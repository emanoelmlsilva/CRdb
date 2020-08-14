package com.mini_projeto.crdb.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorite;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Grade> grades;

    public boolean isValid() {
        return !this.name.isBlank() && !this.lastName.isBlank() && !this.email.isBlank() && !this.password.isBlank();
    }

}