package com.revolut.app.exceptions;

import java.util.concurrent.CompletionException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revolut.app.exceptions.custom.CustomException;
import com.revolut.app.exceptions.model.Response;

@ControllerAdvice
public class OaExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OaExceptionHandler.class);


    @Autowired
    private HttpServletRequest httpRequest;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Response> handleException(Throwable exception) {

        if (exception instanceof CompletionException) {
            exception = exception.getCause();
        }

        final int exceptionType;
        final String message;
        final HttpHeaders headers;
        final HttpStatus status;

        if (exception instanceof CustomException) {
            final CustomException oaCustomException = (CustomException) exception;

            exceptionType = oaCustomException.getType();
            message = oaCustomException.getMessage();
            headers = oaCustomException.getHttpHeaders();
            status = oaCustomException.getHttpStatus();
        } else if (exception instanceof ServletRequestBindingException) {
            if (exception.getMessage()
                    .contains("Missing request header 'Content-Length' for method parameter of type Integer")) {
                exceptionType = 3;
                message = exception.getMessage();
                headers = new HttpHeaders();
                status = HttpStatus.LENGTH_REQUIRED;
            } else {
                exceptionType = 3;
                message = exception.getMessage();
                headers = new HttpHeaders();
                status = HttpStatus.BAD_REQUEST;
            }
        } else if ((exception instanceof MethodArgumentNotValidException) || (exception instanceof HttpMessageNotReadableException)) {
            exceptionType = 3;
            message = exception.getMessage();
            headers = new HttpHeaders();
            status = HttpStatus.BAD_REQUEST;
        } else {
            exceptionType = 4;
            message = HttpStatus.INTERNAL_SERVER_ERROR.toString();
            headers = new HttpHeaders();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        headers.add("Request-ID", httpRequest.getHeader("Request-ID"));

        LOGGER.error(exception.getMessage(), exception);

        final Response response = new Response(exceptionType, httpRequest.getRequestURL()
                .toString(), message);
        return new ResponseEntity<>(response, headers, status);
    }
}