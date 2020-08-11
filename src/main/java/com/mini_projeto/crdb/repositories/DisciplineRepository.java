package com.mini_projeto.crdb.repositories;

import com.mini_projeto.crdb.models.Discipline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, String> {

}