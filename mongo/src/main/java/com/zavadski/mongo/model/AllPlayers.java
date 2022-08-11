package com.zavadski.mongo.model;

import com.zavadski.model.Player;
import com.zavadski.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllPlayers {

    private String age;
    private List<TeamMongo> teams;

    @Data
    @NoArgsConstructor
    public static class TeamMongo {

        private String teamName;
        private List<PlayerMongo> players;

        public static TeamMongo fromTeam(Team team) {
            TeamMongo teamMongo = new TeamMongo();
            teamMongo.setTeamName(team.getTeamName());
            return teamMongo;
        }

        @Data
        @NoArgsConstructor
        public static class PlayerMongo {

            private String firstName;
            private Integer age;

            public static PlayerMongo fromPlayer(Player player) {
                PlayerMongo playerMongo = new PlayerMongo();
                playerMongo.setFirstName(player.getFirstName() + " " + player.getSurname());
                playerMongo.setAge((int) player.getBirthday().until(LocalDate.now(), ChronoUnit.YEARS));
                return playerMongo;
            }
        }

    }

}
