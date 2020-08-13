package com.mini_projeto.crdb.exceptions;

import java.io.IOException;

public class DisciplineNotFoundException extends IOException {

    public DisciplineNotFoundException() {
        super();
    }

    public DisciplineNotFoundException(String message) {
        super(message);
    }

}