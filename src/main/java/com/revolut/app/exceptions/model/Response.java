package com.revolut.app.exceptions.model;

public class Response {

    private Message message;

    public Response(final int type, final String path, final String responseMessage) {
        message = new Message(type, path, responseMessage);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(final Message message) {
        this.message = message;
    }
}