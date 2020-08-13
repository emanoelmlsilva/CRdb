package com.mini_projeto.crdb.exceptions;

import java.io.IOException;

public class CommentNotBelongException extends IOException {

    public CommentNotBelongException() {
    }

    public CommentNotBelongException(String message) {
        super(message);
    }
}