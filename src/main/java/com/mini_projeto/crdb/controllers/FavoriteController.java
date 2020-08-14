package com.mini_projeto.crdb.controllers;

import com.mini_projeto.crdb.exceptions.DisciplineNotFoundException;
import com.mini_projeto.crdb.exceptions.FavoriteNotBelongException;
import com.mini_projeto.crdb.models.Favorite;
import com.mini_projeto.crdb.services.FavoriteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/like")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/{id_discipline}")
    public ResponseEntity<Favorite> insert(@RequestHeader("Authorization") String token,
            @PathVariable String id_discipline) {

        try {
            return new ResponseEntity<Favorite>(favoriteService.insert(token, id_discipline), HttpStatus.OK);
        } catch (DisciplineNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (FavoriteNotBelongException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}