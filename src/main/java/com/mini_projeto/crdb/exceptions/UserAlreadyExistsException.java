package com.mini_projeto.crdb.exceptions;

public class UserAlreadyExistsException extends IllegalArgumentException {

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}