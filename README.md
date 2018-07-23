# winwinwiki

### Travis Build Status

[![Build Status](https://travis-ci.com/winwinwiki/winwinwiki.svg?branch=master)](https://travis-ci.com/winwinwiki/winwinwiki)

## Development

### Setting up dependencies:

* install Java Development Kit 9
* install maven
* install docker

### Running the service using docker-compose (preferred)

```
mvn package dockerfile:build
docker-compose up
```

The service should be up on http://localhost:8080/greeting

### Running the service w/o using docker-compose

* downloading and creating the postgresql docker container
```
docker run -d --name winwin-dev-db -p 5432:5432 -e POSTGRES_DB=winwindb -e POSTGRES_USER=winwindbuser -e POSTGRES_PASSWORD=winwindbpassword postgres
```

* Starting postgresql server
```
docker start winwin-dev-db
```

* Starting application server

```
./mvnw spring-boot:run
```

The service should be up on http://localhost:8080/greeting
