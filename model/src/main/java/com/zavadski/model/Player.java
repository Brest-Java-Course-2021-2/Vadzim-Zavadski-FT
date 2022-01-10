package com.zavadski.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Player {

    @ApiModelProperty(notes = "Players id")
    private Integer playerId;

    @ApiModelProperty(notes = "Players name")
    private String firstName;

    @ApiModelProperty(notes = "Players surname")
    private String surname;

    @ApiModelProperty(notes = "Players birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+3")
    private LocalDate birthday;

    @ApiModelProperty(notes = "Players team id")
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

    public Player(String firstName, String surname, LocalDate birthday, int teamId) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthday = birthday;
        this.teamId = teamId;
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
