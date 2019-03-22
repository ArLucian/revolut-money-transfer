package com.revolut.app.exceptions.custom;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class BusinessException extends CustomException {

    private static final long serialVersionUID = -1333463663925056578L;

    public BusinessException(final String message) {
        super(ExceptionType.BUSINESS, message);
    }

    public BusinessException(final String message, final HttpStatus status) {
        super(ExceptionType.BUSINESS, message, status);
    }

    public BusinessException(final String message, final HttpHeaders headers, final HttpStatus status) {
        super(ExceptionType.BUSINESS, message, headers, status);
    }

}
