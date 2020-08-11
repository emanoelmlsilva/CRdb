package com.mini_projeto.crdb.controllers;

import com.mini_projeto.crdb.exceptions.CommentAlreadyExistsException;
import com.mini_projeto.crdb.exceptions.DisciplineNotExistsException;
import com.mini_projeto.crdb.models.Comment;
import com.mini_projeto.crdb.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{id_discipline}")
    public ResponseEntity<Comment> insert(@RequestHeader("Authorization") String token, @RequestBody Comment comment,
            @PathVariable String id_discipline) {
        try {
            return new ResponseEntity<Comment>(commentService.insert(token, id_discipline, comment), HttpStatus.OK);
        } catch (CommentAlreadyExistsException erro) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (DisciplineNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}