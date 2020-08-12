package com.mini_projeto.crdb.exceptions;

public class CommentNotExistsException extends IllegalArgumentException {

    public CommentNotExistsException() {
        super();
    }

    public CommentNotExistsException(String message) {
        super(message);
    }

}