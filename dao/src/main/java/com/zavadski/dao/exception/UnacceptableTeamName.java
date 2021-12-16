package com.zavadski.dao.exception;

public class UnacceptableTeamName extends IllegalArgumentException  {
    public UnacceptableTeamName(String description) {
        super(description);
    }
}
