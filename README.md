[![Java CI with Maven](https://github.com/Brest-Java-Course-2021-2/Vadzim-Zavadski-FT/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/Brest-Java-Course-2021-2/Vadzim-Zavadski-FT/actions/workflows/maven.yml)

# Vadzim-Zavadski-FT

This is sample 'Football team' web application.
## Requirements
* JDK 11
* Apache Maven3+
        
#### Check environment configuration

        $ java -version
        $ mvn -version        

### Installing
Choose directory for project, download project from github:
 
       $ git clone https://github.com/Brest-Java-Course-2021-2/Vadzim-Zavadski-FT.gitt

#### Build project
Run terminal command in project directory:

        $ mvn clean install

#### Use embedded jetty server for REST application test
   Run terminal command in project directory:

        $ mvn -pl rest-app/ jetty:run

        Once started, the REST server should be available at:

        http://localhost:8088        

Try CURL:
- Team Controller

        Get all teams:
        $ curl -X GET "http://localhost:8088/teams" | json_pp
        
        Create team:
        curl -X POST "http://localhost:8088/teams" -H "accept: application/json" -H "Content-Type: application/json" -d "{"teamName": "Arsenal2"}"
        
        Update team:
        curl -X PUT "http://localhost:8088/teams" -H "accept: application/json" -H "Content-Type: application/json" -d "{"teamId": 2, "teamName": "Arsenal3"}"
        
        Find team by id:
        curl -X GET "http://localhost:8088/teams/1" -H "accept: */*"
        
        Delete team:
        curl -X DELETE "http://localhost:8088/teams/1" -H "accept: application/json"

- TeamDto Controller
        
        Get all teams withaverage age and total players in team:
        curl -X GET "http://localhost:8088/team_dtos" -H "accept: */*" | json_pp
        
- Player Controller
        
        Get all players:
        $ curl -X GET "http://localhost:8088/players" | json_pp
        
        Create Player:
        curl -X POST "http://localhost:8088/players" -H "accept: application/json" -H "Content-Type: application/json" -d "{
        "firstName": "Alexandr",
        "surname": "Lacazette",
        "birthday": "1991-05-28",
        "teamId": 2}"
        
        Update Player:
        curl -X PUT "http://localhost:8088/players" -H "accept: application/json" -H "Content-Type: application/json" -d "{
        "firstName": "Alexandr",
        "surname": "Lacazette",
        "birthday": "1991-05-28",
        "teamId": 2}"
        
        Find player by id:
        curl -X GET "http://localhost:8088/players/1" -H "accept: */*"
        
        Delete player:
        curl -X DELETE "http://localhost:8088/players/1" -H "accept: application/json"
        
- Player Dto Controller
        
        Get players with filter by date of birthday:
        curl -s -X GET "localhost:8088/player_dtos?startDate=1992-01-01&endDate=1994-01-01" | json_pp
        
#### Use embedded jetty server for WEB RESTful application test
You need to run these commands in different tabs or terminal windows:
        
        $ mvn -pl rest-app/ jetty:run

        $ mvn -pl web-app/ jetty:run

   Once started, the application should be available at:

        http://localhost:8080

   if you wanna shutdown jetty server, go to terminal tab or window
   you wanna stop and press "CTRL+C".
