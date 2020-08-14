package com.mini_projeto.crdb.controllers;

import com.mini_projeto.crdb.exceptions.NotExistsException;
import com.mini_projeto.crdb.models.ProfielDiscipline;
import com.mini_projeto.crdb.services.ProfielDisciplineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/profiel_discipline")
public class ProfielDisciplineController {

    @Autowired
    private ProfielDisciplineService profielDisciplineService;

    @GetMapping("/{code}")
    public ResponseEntity<ProfielDiscipline> findByCode(@RequestHeader("Authorization") String token,
            @PathVariable String code) {

        try {

            return new ResponseEntity<ProfielDiscipline>(profielDisciplineService.findProfielDiscipline(token, code),
                    HttpStatus.OK);
        } catch (NotExistsException erro) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}