package com.mini_projeto.crdb.repositories;

import java.util.List;
import java.util.Optional;

import com.mini_projeto.crdb.models.Discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, String> {

    List<Discipline> findByNameContainsIgnoreCase(String name);

    Optional<Discipline> findByCode(String code);

}