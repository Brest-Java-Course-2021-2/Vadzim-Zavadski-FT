package com.zavadski.dao.jdbc.exception;

public class TeamWithPlayerException extends RuntimeException  {
    public TeamWithPlayerException(String description) {
        super(description);
    }
}
