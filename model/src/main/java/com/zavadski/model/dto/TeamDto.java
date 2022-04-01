package com.zavadski.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class TeamDto {

    @ApiModelProperty(notes = "Teams id")
    private Integer teamId;

    @ApiModelProperty(notes = "Teams name")
    private String teamName;

    @ApiModelProperty(notes = "Number of players in team")
    private Integer numberOfPlayers;


    public TeamDto() {
    }

    public TeamDto(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(final Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(final String teamName) {
        this.teamName = teamName;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(final Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    @Override
    public String toString() {
        return "TeamDto{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                '}';
    }
}
