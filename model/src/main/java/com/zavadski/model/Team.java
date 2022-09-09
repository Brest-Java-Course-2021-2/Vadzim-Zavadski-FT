package com.zavadski.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.ConstructorParameters;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @ApiModelProperty(notes = "Teams id")
    private Integer teamId;

    @ApiModelProperty(notes = "Teams name")
    private String teamName;

    @ApiModelProperty(notes = "Teams description")
    private String description;

    public Team(String teamName, String description) {
        this.teamName = teamName;
        this.description = description;
    }
}
