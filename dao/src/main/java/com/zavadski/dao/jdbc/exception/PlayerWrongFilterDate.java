package com.zavadski.dao.jdbc.exception;

public class PlayerWrongFilterDate extends RuntimeException  {
    public PlayerWrongFilterDate(String description) {
        super(description);
    }
}
