DROP TABLE IF EXISTS player;

DROP TABLE IF EXISTS team;
        CREATE TABLE team(
        team_id INT NOT NULL auto_increment,
        team_name VARCHAR(30) NOT NULL UNIQUE,
        CONSTRAINT team_pk PRIMARY KEY (team_id)
        );

CREATE TABLE player (
    player_id int NOT NULL auto_increment,
    first_name varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    birthday date NOT NULL,
    team_id int NOT NULL,
        CONSTRAINT player_pk PRIMARY KEY (player_id),
        CONSTRAINT player_team_fk FOREIGN KEY (team_id) REFERENCES team(team_id)
);