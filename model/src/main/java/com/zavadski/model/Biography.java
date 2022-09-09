package com.zavadski.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Biography {

    @ApiModelProperty(notes = "Biography id")
    private Integer biographyId;

    @ApiModelProperty(notes = "text of Biography")
    private String biography;

    @ApiModelProperty(notes = "Biography Player id")
    private Integer playerId;

}
