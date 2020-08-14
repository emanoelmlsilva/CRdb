package com.mini_projeto.crdb.exceptions;

public class GradeAlreadyExistsException extends IllegalArgumentException {

    public GradeAlreadyExistsException() {
        super();
    }

    public GradeAlreadyExistsException(String message) {
        super(message);
    }

}