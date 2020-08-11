package com.mini_projeto.crdb.repositories;

import com.mini_projeto.crdb.models.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}