package com.zavadski.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    @ApiModelProperty(notes = "Teams id")
    private Integer teamId;

    @ApiModelProperty(notes = "Teams name")
    private String teamName;

    @ApiModelProperty(notes = "Teams description")
    private String description;

    @ApiModelProperty(notes = "Number of players in team")
    private Integer numberOfPlayers;

}
