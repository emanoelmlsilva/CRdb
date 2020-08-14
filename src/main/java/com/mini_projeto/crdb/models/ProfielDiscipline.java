package com.mini_projeto.crdb.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ProfielDiscipline {

    @Id
    private String name;

    @ElementCollection
    private List<Comment> listComment;

    private Double arithmeticAverage;

    private int numericFavorite;
    private Boolean favorite;
}