package com.zavadski.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

public class Player {

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Integer playerId;
    private String firstName;
    private String surname;
    private Date birthday;
    private Integer teamId;

    public Player() {
    }

    public Player(String firstName) {
        this.firstName = firstName;
    }

    public Player(Integer playerId, String firstName) {
        this.playerId = playerId;
        this.firstName = firstName;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", teamId=" + teamId +
                '}';
    }
}