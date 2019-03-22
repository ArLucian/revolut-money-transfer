package com.revolut.app.exceptions.custom;

public class ServerException extends CustomException {

    /**
     *
     */
    private static final long serialVersionUID = 9012179845698589217L;

    public ServerException() {
        super(ExceptionType.SERVER, "An internal server error has occured");
    }

    public ServerException(final String message) {
        super(ExceptionType.SERVER, message);
    }

}
