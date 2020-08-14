package com.mini_projeto.crdb.exceptions;

import java.io.IOException;

public class GradeNotBelongOfUserException extends IOException {

    public GradeNotBelongOfUserException() {
    }

    public GradeNotBelongOfUserException(String message) {
        super(message);
    }
}