package com.zavadski.model.mongo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
public class Player {

    private String firstName;
    private Integer age;

    public static Player fromPlayer(com.zavadski.model.Player player) {
        Player playerMongo = new Player();
        playerMongo.setFirstName(player.getFirstName() + " " + player.getSurname());
        playerMongo.setAge((int) player.getBirthday().until(LocalDate.now(), ChronoUnit.YEARS));
        return playerMongo;
    }
}
