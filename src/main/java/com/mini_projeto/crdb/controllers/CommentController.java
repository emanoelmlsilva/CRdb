package com.mini_projeto.crdb.controllers;

import com.mini_projeto.crdb.dtos.CommentDTO;
import com.mini_projeto.crdb.dtos.CommentDeletedDTO;
import com.mini_projeto.crdb.exceptions.CommentAlreadyExistsException;
import com.mini_projeto.crdb.exceptions.CommentNotBelongException;
import com.mini_projeto.crdb.exceptions.CommentNotExistsException;
import com.mini_projeto.crdb.exceptions.CommentRemovedException;
import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.models.Comment;
import com.mini_projeto.crdb.services.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Comment> insert(@RequestHeader("Authorization") String token,
            @RequestBody CommentDTO commentDTO, @PathVariable String id_discipline) {
        try {
            return new ResponseEntity<Comment>(commentService.insert(token, id_discipline, commentDTO), HttpStatus.OK);
        } catch (CommentAlreadyExistsException erro) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (DisciplineNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> update(@RequestHeader("Authorization") String token,
            @RequestBody CommentDTO commentDTO, @PathVariable Long id) {
        try {
            return new ResponseEntity<Comment>(commentService.update(token, id, commentDTO), HttpStatus.OK);
        } catch (CommentAlreadyExistsException erro) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (DisciplineNotFoundException | CommentRemovedException | CommentNotExistsException
                | CommentNotBelongException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> delete(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        try {
            return new ResponseEntity<Comment>(commentService.delete(token, id), HttpStatus.OK);
        } catch (CommentNotExistsException | CommentNotBelongException erro) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (CommentRemovedException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

}