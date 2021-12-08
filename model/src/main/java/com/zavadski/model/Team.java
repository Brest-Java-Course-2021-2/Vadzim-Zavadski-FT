package com.zavadski.model;

public class Team {

    private Integer teamId;
    private String teamName;

    public Team() {
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(Integer teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public Team setTeamId(Integer teamId) {
        this.teamId = teamId;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public Team setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
