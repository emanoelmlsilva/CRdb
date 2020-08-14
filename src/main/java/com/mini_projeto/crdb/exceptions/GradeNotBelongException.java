package com.mini_projeto.crdb.exceptions;

import java.io.IOException;

public class GradeNotBelongException extends IOException {

    public GradeNotBelongException() {
    }

    public GradeNotBelongException(String message) {
        super(message);
    }
}