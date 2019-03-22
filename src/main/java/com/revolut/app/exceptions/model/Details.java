package com.revolut.app.exceptions.model;

import com.revolut.app.exceptions.util.OaDateTimeFormatter;

public class Details {

    private String path;
    private final String timestamp;
    private String message;

    public Details(final String path, final String message) {
        this.path = path;
        this.timestamp = OaDateTimeFormatter.getCurrentDateTimeAsString();
        this.message = message;

    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}