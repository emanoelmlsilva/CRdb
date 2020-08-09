package com.mini_projeto.crdb.exceptions;

public class UserInvalidException extends IllegalArgumentException {

    public UserInvalidException() {
        super();
    }

    public UserInvalidException(String message) {
        super(message);
    }

}