package com.mini_projeto.crdb.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "code" })
public class Discipline {

    @Id
    String code;

    private String name;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Grade> grades;

}