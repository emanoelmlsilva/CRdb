package com.mini_projeto.crdb.exceptions;

import java.io.IOException;

public class GradeNotExistsException extends IOException {

    public GradeNotExistsException() {
    }

    public GradeNotExistsException(String message) {
        super(message);
    }
}