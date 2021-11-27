# Vadzim-Zavadski-FT

This is sample 'Football team' web application.
## Requirements
* JDK 11
* Apache Maven
## Build application:
```
mvn clean install
```

## Run integration tests:
```
mvn clean verify
```

## Run project information ( coverage, dependency, etc. ):
```
mvn site
mvn site:stage
open in browser: ${project}/target/staging/index.html
```