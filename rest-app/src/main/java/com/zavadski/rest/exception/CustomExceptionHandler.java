package com.zavadski.rest.exception;

import com.zavadski.dao.exception.PlayerWrongFilterDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalArgumentException(Exception ex) {
        return new ResponseEntity<>(String.format("Handle: %s", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(PlayerWrongFilterDate.class)
    public ResponseEntity<String> handlePlayerWrongFilterDate(Exception ex) {
        return new ResponseEntity<>(String.format("Handle: %s", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

    }
}