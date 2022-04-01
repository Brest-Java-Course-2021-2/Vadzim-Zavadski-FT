package com.zavadski.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    @ApiModelProperty(notes = "Players id")
    private Integer playerId;

    @ApiModelProperty(notes = "Players firstName")
    private String firstName;

    @ApiModelProperty(notes = "Players surname")
    private String surname;

    @ApiModelProperty(notes = "Players birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+3")
    private LocalDate birthday;

    @ApiModelProperty(notes = "Teams id")
    private Integer teamId;

}
