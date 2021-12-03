package com.zavadski.model.dto;

/**
 * POJO Team for model.
 */
public class TeamDto {

    /**
     * Team Id.
     */
    private Integer teamId;

    /**
     * Team Name.
     */
    private String teamName;

    /**
     * Number of Players in Team.
     */
    private Integer numberOfPlayers;

    private Double avgAge;

    public Double getAvgAge() {
        return avgAge;
    }

    public void setAvgAge(final Double avgAge) {
        this.avgAge = avgAge;
    }

    /**
     * Constructor without arguments.
     */
    public TeamDto() {
    }

    /**
     * Constructor with team name.
     *
     * @param teamName team name
     */
    public TeamDto(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Returns <code>Integer</code> representation of this teamId.
     *
     * @return teamId Team Id.
     */
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * Sets the team's identifier.
     *
     * @param teamId Team Id.
     */
    public void setTeamId(final Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * Returns <code>String</code> representation of this teamName.
     *
     * @return teamName Team Name.
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Sets the team's name.
     *
     * @param teamName Team Name.
     */
    public void setTeamName(final String teamName) {
        this.teamName = teamName;
    }

    /**
     * Returns <code>Integer</code> representation number of Players
     * for the Team.
     *
     * @return teamId.
     */
    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Sets the number of team's Players.
     *
     * @param numberOfPlayers Number of Players
     */
    public void setNumberOfPlayers(final Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * {@inheritDoc}
     */

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
