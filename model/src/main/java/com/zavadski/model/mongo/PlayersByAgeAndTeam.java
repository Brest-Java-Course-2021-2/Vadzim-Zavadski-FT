package com.zavadski.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayersByAgeAndTeam {

    private String age;
    private List<TeamMongo> teams;

}
