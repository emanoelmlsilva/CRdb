package com.mini_projeto.crdb.repositories;

import java.util.Optional;

import com.mini_projeto.crdb.models.Favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.discipline.code = :code AND f.user.email = :email")
    public Optional<Favorite> findByCode(@Param("code") String code, @Param("email") String email);

}