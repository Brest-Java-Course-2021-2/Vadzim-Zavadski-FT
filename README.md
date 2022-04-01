[![Java CI with Maven](https://github.com/Brest-Java-Course-2021-2/Vadzim-Zavadski-FT/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/Brest-Java-Course-2021-2/Vadzim-Zavadski-FT/actions/workflows/maven.yml)

# Vadzim-Zavadski-FT

This is sample 'Football team' web application.
- [Software requirements specification](specification/specification.md)

## Requirements
* JDK 11
* Apache Maven3+

#### Check environment configuration

        java -version
        mvn -version        

### Installing
Choose directory for project, download project from github:

       git clone https://github.com/Brest-Java-Course-2021-2/Vadzim-Zavadski-FT.git

#### Build project
Run terminal command in project directory:

        mvn clean install


### Run application
Application consists 2 particular modules (web-application & rest-app) that are dependent on each other.
### Start Rest application
To start Rest server (rest-app module) with H2 Database:
```
java -jar ./rest-app/target/rest-app-1.0-SNAPSHOT.jar
java -jar ./rest-app/target/rest-app-1.0-SNAPSHOT.jar --spring.profiles.active=h2
```
To start Rest server (rest-app module) with PostgreSQL Database you should enter your ***username*** and ***password*** for database:
```
java -Ddb_user=epam -Ddb_pass=epam -jar ./rest-app/target/rest-app-1.0-SNAPSHOT.jar --spring.profiles.active=postgresql
```
rest-app is set up on http://localhost:8088 (no default redirect provided)
### Start Web application
To start WEB application (web-application module):
```
java -jar ./web-application/target/web-application-1.0-SNAPSHOT.jar
```
web-application is set up on http://localhost:8080 and is listening to :8088 so rest-app must be started already.
### Stop applications
To trigger Rest Shutdown Hook:
```
^C
```
## Rest API
There is an API provided for the rest-app in the project. To access it, go to the
```
http://localhost:8088/swagger-ui.html
```
Note: Rest app should be built and ran for api page to work.
