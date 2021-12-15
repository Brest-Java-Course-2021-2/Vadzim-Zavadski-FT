package com.zavadski.model.dto;

public class TeamDto {


    private Integer teamId;
    private String teamName;
    private Integer numberOfPlayers;
    private Double avgAge;

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

    public Double getAvgAge() {
        return avgAge;
    }

    public void setAvgAge(final Double avgAge) {
        this.avgAge = avgAge;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                ", avgAge=" + avgAge +
                '}';
    }
}
