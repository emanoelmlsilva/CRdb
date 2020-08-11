package com.mini_projeto.crdb.exceptions;

import java.io.IOException;

public class DisciplineNotExistsException extends IOException {

    public DisciplineNotExistsException() {
        super();
    }

    public DisciplineNotExistsException(String message) {
        super(message);
    }

}