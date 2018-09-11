#!/bin/bash

export WINWIN_DB_HOST=localhost
export WINWIN_DB_NAME=winwindb
export WINWIN_DB_USER=winwindbuser
export WINWIN_DB_PASSWORD=winwindbpassword

./mvnw spring-boot:run
