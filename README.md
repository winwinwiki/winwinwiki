# winwinwiki

### Travis Build Status

[![Build Status](https://travis-ci.com/winwinwiki/winwinwiki.svg?branch=master)](https://travis-ci.com/winwinwiki/winwinwiki)

## Development

### Setting up dependencies:

* install Java Development Kit 10: 
* install maven: [setup instructions](https://maven.apache.org/install.html)
* install docker: [setup instructions](https://docs.docker.com/install/)
* install lombok plugin for your IDE: [setup documentation](https://projectlombok.org/setup/overview)

### Running the service using docker-compose (preferred)

```
mvn package dockerfile:build
docker-compose up
```

The service should be up on port 80. all apis are listed in http://localhost:80/swagger-ui.html

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
export WINWIN_DB_HOST=localhost
export WINWIN_DB_NAME=winwindb
export WINWIN_DB_USER=winwindbuser
export WINWIN_DB_PASSWORD=winwindbpassword
./mvnw spring-boot:run
```

The service should be up on port 80. all apis are listed in http://localhost:80/swagger-ui.html
