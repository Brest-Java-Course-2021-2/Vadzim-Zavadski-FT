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

insert into TEAM (team_name) values ('Liverpool');
insert into TEAM (team_name) values ('Arsenal');
insert into TEAM (team_name) values ('Lester');

insert into PLAYER (first_name, surname, birthday, team_id) values ('Sadio', 'Mane', '1992-4-10', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Roberto', 'Firmino', '1991-10-2', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Mohamed', 'Salah', '1992-06-15', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Alexandre', 'Lacazette', '1991-05-28', 2);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Bukayo', 'Saka', '2001-09-05', 2);
