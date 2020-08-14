package com.mini_projeto.crdb.controllers;

import java.util.List;

import com.mini_projeto.crdb.dtos.DisciplineDTO;
import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.models.Discipline;
import com.mini_projeto.crdb.services.DisciplineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("")
    public ResponseEntity<List<DisciplineDTO>> findAll() {
        return new ResponseEntity<List<DisciplineDTO>>(disciplineService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<DisciplineDTO>> findAll(@PathVariable String name) {
        try {
            return new ResponseEntity<List<DisciplineDTO>>(disciplineService.findByName(name), HttpStatus.OK);
        } catch (DisciplineNotFoundException erro) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}