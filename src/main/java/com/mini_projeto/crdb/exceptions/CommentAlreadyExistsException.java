package com.mini_projeto.crdb.exceptions;

public class CommentAlreadyExistsException extends IllegalArgumentException {

    public CommentAlreadyExistsException() {
        super();
    }

    public CommentAlreadyExistsException(String message) {
        super(message);
    }

}