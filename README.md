# winwinwiki

### Travis Build Status

[![Build Status](https://travis-ci.com/winwinwiki/winwinwiki.svg?branch=master)](https://travis-ci.com/winwinwiki/winwinwiki)

### Running the service

* **Using docker (preferred)**
```
mvn package dockerfile:build
docker run -p 8080:8080 winwinwiki/winwinwiki:0.1.0
```

* **Without using docker**
```
./mvnw spring-boot:run
```

The service should be up on http://localhost:8080/greeting
