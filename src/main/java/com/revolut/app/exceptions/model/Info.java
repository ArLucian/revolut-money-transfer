package com.revolut.app.exceptions.model;

public class Info {

    private Integer type;
    private Details details;

    public Info(final int type, final String path, final String exceptionMessage) {
        this.type = type;
        this.details = new Details(path, exceptionMessage);
    }

    public Integer getType() {
        return type;
    }

    public void setType(final Integer type) {
        this.type = type;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(final Details details) {
        this.details = details;
    }
}