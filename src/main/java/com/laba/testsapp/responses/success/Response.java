package com.laba.testsapp.responses.success;

import java.io.Serializable;

public class Response implements Serializable {
    protected String message;

    public Response (String message) {
        this.message = message;
    }

    public Response () {}

    public String getMessage () {
        return message;
    }
}
