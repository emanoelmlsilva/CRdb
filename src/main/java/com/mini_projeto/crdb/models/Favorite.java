package com.mini_projeto.crdb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(of = { "id" })
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;

    @JsonIgnore
    @JoinColumn(name = "discipline_id")
    @ManyToOne
    private Discipline discipline;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Favorite() {
        this.active = true;
    }

}