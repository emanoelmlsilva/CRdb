package com.mini_projeto.crdb.repositories;

import java.util.Optional;

import com.mini_projeto.crdb.models.Grade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("SELECT g FROM Grade g WHERE g.discipline.code = :code AND g.user.email = :email")
    public Optional<Grade> findByCode(@Param("code") String code, @Param("email") String email);
}