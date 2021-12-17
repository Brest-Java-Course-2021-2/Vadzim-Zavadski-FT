package com.zavadski.dao.exception;

public class TeamWithPlayerException extends RuntimeException  {
    public TeamWithPlayerException(String description) {
        super(description);
    }
}
