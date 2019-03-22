package com.revolut.app.exceptions.custom;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * This is the custom OA Exception class used to encapsulate all the exception that can be thrown by the system, forcing the error to be customized
 * with the resourceId and message value.
 *
 * @author Alexandru Puscasu
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -6212956324556815516L;

    /**
     * The type of the exception: BUSINESS, SERVER.
     */
    public enum ExceptionType {

        BUSINESS(3), SERVER(4);

        private int id;

        ExceptionType(final int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private final int type;

    private final HttpHeaders headers;

    private final HttpStatus status;

    public CustomException(final ExceptionType type, final String message) {
        super(message);
        this.type = type.getId();
        this.headers = new HttpHeaders();
        if (ExceptionType.BUSINESS.equals(type)) {
            this.status = HttpStatus.BAD_REQUEST;
        } else {
            this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public CustomException(final ExceptionType type, final String message, final HttpStatus status) {
        super(message);
        this.type = type.getId();
        this.headers = new HttpHeaders();
        this.status = status;
    }

    public CustomException(final ExceptionType type, final String message, final HttpHeaders headers, final HttpStatus status) {
        super(message);
        this.type = type.getId();
        this.headers = headers;
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public HttpHeaders getHttpHeaders() {
        return headers;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }
}