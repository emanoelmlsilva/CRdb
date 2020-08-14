package com.mini_projeto.crdb.exceptions;

public class NotExistsException extends IllegalArgumentException {

    public NotExistsException() {
        super();
    }

    public NotExistsException(String message) {
        super(message);
    }

}