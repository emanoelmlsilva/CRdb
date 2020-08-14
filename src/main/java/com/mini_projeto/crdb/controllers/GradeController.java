package com.mini_projeto.crdb.controllers;

import com.mini_projeto.crdb.dtos.GradeDTO;
import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.exceptions.GradeNotBelongException;
import com.mini_projeto.crdb.exceptions.GradeNotBelongOfUserException;
import com.mini_projeto.crdb.exceptions.GradeNotExistsException;
import com.mini_projeto.crdb.models.Grade;
import com.mini_projeto.crdb.services.GradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("/{id_discipline}")
    public ResponseEntity<GradeDTO> insert(@RequestHeader("Authorization") String token,
            @PathVariable String id_discipline, @RequestBody Grade grade) {

        try {
            return new ResponseEntity<GradeDTO>(gradeService.insert(token, id_discipline, grade), HttpStatus.OK);
        } catch (DisciplineNotFoundException erro) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GradeDTO> update(@RequestHeader("Authorization") String token, @PathVariable Long id,
            @RequestBody GradeDTO gradeDTO) {
        try {
            return new ResponseEntity<GradeDTO>(gradeService.update(token, gradeDTO, id), HttpStatus.OK);
        } catch (GradeNotExistsException erro) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (GradeNotBelongException erro) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}