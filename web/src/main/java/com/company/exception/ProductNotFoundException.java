package com.company.exception;

/**
 * Created by Kate M on 18.03.2018.
 */
public class ProductNotFoundException extends Exception {

    private String message;

    public ProductNotFoundException(String message) {
        this.message = System.currentTimeMillis() + ": " + message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
