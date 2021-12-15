package com.zavadski.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class Player {

    private Integer playerId;
    private String firstName;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public Player setTeamId(Integer teamId) {
        this.teamId = teamId;
        return this;
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