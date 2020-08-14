package com.mini_projeto.crdb.exceptions;

import java.io.IOException;

public class FavoriteNotBelongException extends IOException {

    public FavoriteNotBelongException() {
    }

    public FavoriteNotBelongException(String message) {
        super(message);
    }
}