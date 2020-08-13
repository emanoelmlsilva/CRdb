package com.mini_projeto.crdb.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.mini_projeto.crdb.models.Comment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
public class CommentDeletedDTO {

    private Long id;

    private LocalDate localDate;

    private LocalTime localTime;

    private String comment;

    public CommentDeletedDTO(Comment comment) {

        this.id = comment.getId();
        this.localDate = comment.getLocalDate();
        this.localTime = comment.getLocalTime();
        this.comment = comment.getDeleted() ? "" : comment.getComment();
    }

}