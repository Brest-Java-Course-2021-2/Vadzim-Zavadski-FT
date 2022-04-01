package com.zavadski.rest.exception;

import com.zavadski.dao.jdbc.exception.FieldNullPointerException;
import com.zavadski.dao.jdbc.exception.PlayerWrongFilterDate;
import com.zavadski.dao.jdbc.exception.TeamWithPlayerException;
import com.zavadski.dao.jdbc.exception.UnacceptableName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    public static final String TEAM_NOT_FOUND = "team.not_found";
    public static final String VALIDATION_ERROR = "validation_error";

    @ExceptionHandler(value = {UnacceptableName.class})
    public ResponseEntity<String> handleUnacceptableName(Exception ex) {
        return new ResponseEntity<>(String.format("Handle: %s", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(PlayerWrongFilterDate.class)
    public ResponseEntity<String> handlePlayerWrongFilterDate(Exception ex) {
        return new ResponseEntity<>(String.format("Handle: %s", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(FieldNullPointerException.class)
    public ResponseEntity<String> handleFieldNullPointerException(Exception ex) {
        return new ResponseEntity<>(String.format("Handle: %s", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(TeamWithPlayerException.class)
    public ResponseEntity<String> handleTeamWithPlayerException(Exception ex) {
        return new ResponseEntity<>(String.format("Handle: %s", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(String.format("Handle: %s", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
