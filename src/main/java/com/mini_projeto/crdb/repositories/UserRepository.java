package com.mini_projeto.crdb.repositories;

import java.util.Optional;

import com.mini_projeto.crdb.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}