package com.mini_projeto.crdb.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    // @ManyToMany
    // @JoinTable(name = "Discipline_User", joinColumns = @JoinColumn(name =
    // "discipline_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    // private List<User> users;

}