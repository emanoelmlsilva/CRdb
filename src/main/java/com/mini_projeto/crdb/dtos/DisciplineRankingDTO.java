package com.mini_projeto.crdb.dtos;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.mini_projeto.crdb.models.Discipline;

import lombok.Data;

@Data
public class DisciplineRankingDTO {

    private String name;

    private Double gride;

    public DisciplineRankingDTO(Discipline discipline) {
        this.name = discipline.getName();
        this.gride = discipline.getGrades().size() > 0
                ? discipline.getGrades().stream().sorted().collect(Collectors.toList()).get(0).getGrade()
                : 0;
    }

}