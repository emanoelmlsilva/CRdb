package com.mini_projeto.crdb.dtos;

import com.mini_projeto.crdb.models.Grade;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class GradeDTO {

    private Long id;

    private Double grade;

    public GradeDTO(Grade grade) {
        this.id = grade.getId();
        this.grade = grade.getGrade();
    }
}