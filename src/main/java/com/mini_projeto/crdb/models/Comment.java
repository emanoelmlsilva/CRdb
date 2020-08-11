package com.mini_projeto.crdb.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotNull
    private LocalDate localDate;

    // @NotNull
    private LocalTime localTime;

    @NotBlank
    private String comment;

    // @NotEmpty
    private boolean active;

    @JsonIgnore
    @JoinColumn(name = "discipline_id")
    @ManyToOne
    private Discipline discipline;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
}