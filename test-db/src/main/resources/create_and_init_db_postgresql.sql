drop table IF EXISTS player;

drop table IF EXISTS team;

create TABLE team(
team_id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
team_name VARCHAR(30) NOT NULL UNIQUE
);

create TABLE player (
player_id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
first_name varchar(50) NOT NULL,
surname varchar(50) NOT NULL,
birthday date NOT NULL,
team_id integer NOT NULL,
CONSTRAINT player_team_fk FOREIGN KEY (team_id) REFERENCES team(team_id)
);

insert into TEAM (team_name) values ('Liverpool1');
insert into TEAM (team_name) values ('Arsenal');
insert into TEAM (team_name) values ('Lester');

insert into PLAYER (first_name, surname, birthday, team_id) values ('Sadio', 'Mane', '1992-4-10', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Roberto', 'Firmino', '1991-10-2', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Mohamed', 'Salah', '1992-06-15', 1);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Alexandre', 'Lacazette', '1991-05-28', 2);
insert into PLAYER (first_name, surname, birthday, team_id) values ('Bukayo', 'Saka', '2001-09-05', 2);
