package com.mini_projeto.crdb.exceptions;

import java.io.IOException;

public class CommentRemovedException extends IOException {

    public CommentRemovedException() {
    }

    public CommentRemovedException(String message) {
        super(message);
    }
}