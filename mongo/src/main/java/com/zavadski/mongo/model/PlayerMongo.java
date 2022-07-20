package com.zavadski.mongo.model;

import com.zavadski.model.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
public class PlayerMongo {

    private String firstName;
    private Integer age;

    public static PlayerMongo fromPlayer(Player player) {
        PlayerMongo playerMongo = new PlayerMongo();
        playerMongo.setFirstName(player.getFirstName() + " " + player.getSurname());
        playerMongo.setAge((int) player.getBirthday().until(LocalDate.now(), ChronoUnit.YEARS));
        return playerMongo;
    }
}
