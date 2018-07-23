# winwinwiki

### Travis Build Status

[![Build Status](https://travis-ci.com/winwinwiki/winwinwiki.svg?branch=master)](https://travis-ci.com/winwinwiki/winwinwiki)

## Development

### Setting up dependencies:

* install Java Development Kit 9
* install maven
* install docker
* downloading and creating the postgresql docker container
```
docker run -d --name winwin-dev-db -p 5432:5432 -e POSTGRES_DB=winwindb -e POSTGRES_USER=winwindbuser -e POSTGRES_PASSWORD=winwindbpassword postgres
```

### Running dependencies:

* **Starting postgresql server**
```
docker start winwin-dev-db
```

### Running the service

* **Using docker (preferred)**
```
mvn package dockerfile:build
docker run --name winwinwiki-java-service -p 8080:8080 winwinwiki/winwinwiki:0.1.0
```

* **Without using docker**
```
./mvnw spring-boot:run
```

The service should be up on http://localhost:8080/greeting
