package com.zavadski.dao.jdbc.exception;

public class UnacceptableName extends IllegalArgumentException  {
    public UnacceptableName(String description) {
        super(description);
    }
}
