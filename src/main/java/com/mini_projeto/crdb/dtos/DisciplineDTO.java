package com.mini_projeto.crdb.dtos;

import com.mini_projeto.crdb.models.Discipline;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DisciplineDTO {

    private String id;

    private String name;

    public DisciplineDTO(Discipline discipline) {
        this.id = discipline.getCode();
        this.name = discipline.getName();
    }
}