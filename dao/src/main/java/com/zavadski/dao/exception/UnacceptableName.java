package com.zavadski.dao.exception;

public class UnacceptableName extends IllegalArgumentException  {
    public UnacceptableName(String description) {
        super(description);
    }
}
