DROP TABLE IF EXISTS team;
        CREATE TABLE team(
        teamId INT NOT NULL AUTO_INCREMENT,
        teamName VARCHAR(20) NOT NULL UNIQUE,
        PRIMARY KEY (teamId)
        );